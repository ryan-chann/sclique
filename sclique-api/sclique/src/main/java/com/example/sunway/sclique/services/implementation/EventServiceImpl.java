package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.Event;
import com.example.sunway.sclique.entities.EventFee;
import com.example.sunway.sclique.entities.EventSession;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import com.example.sunway.sclique.mapper.IEventMapper;
import com.example.sunway.sclique.models.*;
import com.example.sunway.sclique.repositories.IEventFeeRepository;
import com.example.sunway.sclique.repositories.IEventRepository;
import com.example.sunway.sclique.repositories.IEventSessionRepository;
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
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements IEventService {
    private final IEventRepository eventRepository;
    private final IEventMapper eventMapper;
    private final IEventFeeRepository eventFeeRepository;
    private final IEventSessionRepository eventSessionRepository;
    private final IImageService imageService;

    @Autowired
    public EventServiceImpl(
            IEventRepository eventRepository,
            IEventMapper eventMapper,
            IEventFeeRepository eventFeeRepository,
            IEventSessionRepository eventSessionRepository,
            IImageService imageService
    ) {
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

        Event event = eventMapper.createEventRequestToEvent(createEventRequest);

        Event savedEvent = eventRepository.save(event);

        CreateImageRequest createImageRequest = new CreateImageRequest(
                savedEvent.getId().toString(),
                EntityType.EVENT.getId(),
                ImageType.EVENT_ADVERTISEMENT_IMAGE.getId()
        );

        try{
            imageService.saveImage(eventAdvertisementImage, createImageRequest);
        }
        catch(IOException ioException){
            response.setErrorMessage(ioException.getMessage());
            return response;
        }

        response.setSuccess(true);
        response.setData(Boolean.TRUE);

        return response;
    }

    public ServiceResponse<Page<String>> getEventTitleByMatchingIdOrTitle(SearchEventsRequest searchEventsRequest)
    {
        var response = new ServiceResponse<Page<String>>();

        if (searchEventsRequest == null){
            response.setErrorMessage("Request is null");
            return response;
        }

        Pageable pageable = PageRequest.of(searchEventsRequest.getPage(), searchEventsRequest.getPageSize());

        Page<String> eventTitlePage =  eventRepository.findEventTitleByIdOrTitleContainingIgnoreCase(searchEventsRequest.getQuery(), pageable);

        response.setData(eventTitlePage);
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

        Page<GetEventSummaryResponse> serviceResponsePage = repositoryResponsePage.map(row -> {
            UUID eventId = (UUID) row[0];
            String eventTitle = (String) row[1];
            String eventVenue = (String) row[2];
            byte[] imageBytes = (byte[]) row[3];
            String mimeType = (String) row[4];

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
}
