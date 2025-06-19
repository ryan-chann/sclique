package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.Event;
import com.example.sunway.sclique.entities.Organisation;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import com.example.sunway.sclique.mapper.IEventMapper;
import com.example.sunway.sclique.models.*;
import com.example.sunway.sclique.models.event.*;
import com.example.sunway.sclique.models.image.CreateImageRequest;
import com.example.sunway.sclique.models.image.GetImageByEntityIdResponse;
import com.example.sunway.sclique.repositories.IEventFeeRepository;
import com.example.sunway.sclique.repositories.IEventRepository;
import com.example.sunway.sclique.repositories.IEventSessionRepository;
import com.example.sunway.sclique.repositories.IOrganisationRepository;
import com.example.sunway.sclique.services.IEventService;

import com.example.sunway.sclique.services.IImageService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {
    private final IOrganisationRepository organisationRepository;
    private final IEventRepository eventRepository;
    private final IEventMapper eventMapper;
    private final IEventFeeRepository eventFeeRepository;
    private final IEventSessionRepository eventSessionRepository;
    private final IImageService imageService;

    @Autowired
    public EventServiceImpl(
            IOrganisationRepository organisationRepository,
            IEventRepository eventRepository,
            IEventMapper eventMapper,
            IEventFeeRepository eventFeeRepository,
            IEventSessionRepository eventSessionRepository,
            IImageService imageService
    ) {
        this.organisationRepository = organisationRepository;
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.eventFeeRepository = eventFeeRepository;
        this.eventSessionRepository = eventSessionRepository;
        this.imageService = imageService;
    }

    public ServiceResponse<Boolean> createEvent(CreateEventRequest createEventRequest, MultipartFile eventAdvertisementImage) {
        var response = new ServiceResponse<Boolean>();

        if (createEventRequest == null){
            response.setErrorMessage("Request is null");
            return response;
        }

        Optional<Organisation> organisationOptional = organisationRepository.findById(UUID.fromString(createEventRequest.getOrganiserId()));

        if (organisationOptional.isEmpty()) {
            response.setErrorMessage("Organisation not found");
            return response;
        }

        Organisation organisation = organisationOptional.get();

        Event event = eventMapper.createEventRequestToEvent(createEventRequest);
        event.setOrganiser(organisation);

        Event savedEvent = eventRepository.save(event);

        CreateImageRequest createImageRequest = new CreateImageRequest(
                savedEvent.getId().toString(),
                EntityType.EVENT.getId(),
                ImageType.EVENT_ADVERTISEMENT_IMAGE.getId()
        );

        try {
            imageService.saveImage(eventAdvertisementImage, createImageRequest);
        }
        catch (IOException ioException) {
            response.setErrorMessage(ioException.getMessage());
            return response;
        }

        response.setSuccess(true);
        response.setData(Boolean.TRUE);

        return response;
    }

    public ServiceResponse<Page<GetEventTitleResponse>> getEventTitleByMatchingIdOrTitle(SearchEventsRequest searchEventsRequest)
    {
        var response = new ServiceResponse<Page<GetEventTitleResponse>>();

        if (searchEventsRequest == null){
            response.setErrorMessage("Request is null");
            return response;
        }

        Pageable pageable = PageRequest.of(searchEventsRequest.getPage(), searchEventsRequest.getPageSize());

        Page<Object[]> repositoryResponsePage = eventRepository.findEventTitleByIdOrTitleContainingIgnoreCase(searchEventsRequest.getQuery(), pageable);

        Page<GetEventTitleResponse> serviceResponsePage = repositoryResponsePage.map(object -> {
            UUID eventId = (UUID) object[0];
            String eventTitle = (String) object[1];

            return new GetEventTitleResponse(
                eventId.toString(),
                eventTitle
            );
        });

        response.setData(serviceResponsePage);
        response.setSuccess(true);

        return response;
    }

    @Transactional
    public ServiceResponse<Page<GetEventSummaryResponse>> getEventSummaryByTitleContainingIgnoreCase(SearchEventsRequest searchEventsRequest) {
        var response = new ServiceResponse<Page<GetEventSummaryResponse>>();

        if (searchEventsRequest == null) {
            response.setErrorMessage("Request is null");
            return response;
        }

        Pageable pageable = PageRequest.of(
                searchEventsRequest.getPage(),
                searchEventsRequest.getPageSize()
        );

        Page<Object[]> repositoryResponsePage = eventRepository.findEventSummaryByTitleContainingIgnoreCase(
                searchEventsRequest.getQuery(),
                EntityType.EVENT,
                ImageType.EVENT_ADVERTISEMENT_IMAGE,
                pageable
        );

        Page<GetEventSummaryResponse> serviceResponsePage = repositoryResponsePage.map(object -> {
            UUID eventId = (UUID) object[0];
            String eventTitle = (String) object[1];
            String eventVenue = (String) object[2];
            byte[] imageBytes = (byte[]) object[3];
            String mimeType = (String) object[4];

            List<CreateEventRequest.EventFeeDto> feeDto = eventFeeRepository.findByEventId(eventId).stream()
                    .map(fee -> {
                        var dto = new CreateEventRequest.EventFeeDto();
                        dto.setType(fee.getType());
                        dto.setPrice(fee.getPrice());
                        return dto;
                    })
                    .toList();

            List<CreateEventRequest.EventSessionDto> sessionDto = eventSessionRepository.findByEventId(eventId).stream()
                    .map(session -> {
                        var dto = new CreateEventRequest.EventSessionDto();
                        dto.setSession(session.getSession());
                        return dto;
                    })
                    .toList();

            return new GetEventSummaryResponse(
                eventId.toString(),
                eventTitle,
                eventVenue,
                feeDto,
                sessionDto,
                imageBytes != null ? Base64.getEncoder().encodeToString(imageBytes) : null,
                mimeType
            );
        });

        response.setData(serviceResponsePage);
        response.setSuccess(true);
        return response;
    }

    public ServiceResponse<GetEventProfileResponse> getEventProfileById(GetEventProfileRequest getEventProfileRequest) {
        var response = new ServiceResponse<GetEventProfileResponse>();

        Optional<Event> eventOptional = eventRepository.findById(UUID.fromString(getEventProfileRequest.getEventId()));
        if (eventOptional.isEmpty()) {
            response.setErrorMessage("Event not found");
            return response;
        }
        Event event = eventOptional.get();

        Optional<Organisation> organisationOptional = organisationRepository.findById(event.getOrganiser().getId());
        if (organisationOptional.isEmpty()) {
            response.setErrorMessage("Organisation not found");
            return response;
        }
        Organisation organisation = organisationOptional.get();

        ServiceResponse<List<GetImageByEntityIdResponse>> imageResponse = imageService.getImageByEntityId(event.getId().toString());
        GetEventProfileResponse.ImageDto eventImage = null;

        if (imageResponse.isSuccess() && !imageResponse.getData().isEmpty()) {
            Optional<GetImageByEntityIdResponse> coverImageOpt = imageResponse.getData().stream()
                    .filter(img -> img.getImageType() == ImageType.EVENT_ADVERTISEMENT_IMAGE)
                    .findFirst();

            if (coverImageOpt.isPresent()) {
                GetImageByEntityIdResponse imageData = coverImageOpt.get();
                eventImage = new GetEventProfileResponse.ImageDto();
                eventImage.setMimeType(imageData.getMimeType());
                eventImage.setImageDataBase64(imageData.getImageDataBase64());
            }
        }

        GetEventProfileResponse.OrganiserDto organiserDto = new GetEventProfileResponse.OrganiserDto();
        organiserDto.setName(organisation.getName());

        ServiceResponse<List<GetImageByEntityIdResponse>> organiserImageResponse = imageService.getImageByEntityId(organisation.getId().toString());

        if (organiserImageResponse.isSuccess() && !organiserImageResponse.getData().isEmpty()) {
            Optional<GetImageByEntityIdResponse> logoImageOpt = organiserImageResponse.getData().stream()
                    .filter(img -> img.getImageType() == ImageType.ORGANISATION_PROFILE_IMAGE)
                    .findFirst();

            if (logoImageOpt.isPresent()) {
                GetImageByEntityIdResponse orgImg = logoImageOpt.get();
                GetEventProfileResponse.ImageDto orgImageDto = new GetEventProfileResponse.ImageDto();
                orgImageDto.setMimeType(orgImg.getMimeType());
                orgImageDto.setImageDataBase64(orgImg.getImageDataBase64());
                organiserDto.setOrganiserImage(orgImageDto);
            }
        }

        List<GetEventProfileResponse.EventFeeDto> feeDtos = event.getEventFees().stream()
                .map(fee -> {
                    GetEventProfileResponse.EventFeeDto dto = new GetEventProfileResponse.EventFeeDto();
                    dto.setType(fee.getType());
                    dto.setPrice(fee.getPrice());
                    return dto;
                })
                .collect(Collectors.toList());

        List<GetEventProfileResponse.EventSessionDto> sessionDtos = event.getEventSessions().stream()
                .map(session -> {
                    GetEventProfileResponse.EventSessionDto dto = new GetEventProfileResponse.EventSessionDto();
                    dto.setSession(session.getSession());
                    return dto;
                })
                .collect(Collectors.toList());


        GetEventProfileResponse profile = new GetEventProfileResponse();
        profile.setId(event.getId().toString());
        profile.setName(event.getTitle());
        profile.setVenue(event.getVenue());
        profile.setDurationInMinutes(event.getDurationInMinutes());
        profile.setParticipationLink(event.getParticipationLink());
        profile.setOrganiser(organiserDto);
        profile.setEventFees(feeDtos);
        profile.setEventSessions(sessionDtos);
        profile.setEventImage(eventImage);

        response.setData(profile);
        response.setSuccess(true);
        return response;
    }
}
