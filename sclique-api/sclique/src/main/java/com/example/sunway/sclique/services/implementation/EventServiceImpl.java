package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.Event;
import com.example.sunway.sclique.mapper.IEventMapper;
import com.example.sunway.sclique.models.CreateEventRequest;
import com.example.sunway.sclique.models.SearchEventsRequest;
import com.example.sunway.sclique.models.ServiceResponse;
import com.example.sunway.sclique.repositories.IEventRepository;
import com.example.sunway.sclique.services.IEventService;
import com.example.sunway.sclique.services.IImageService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {
    private final IEventRepository eventRepository;
    private final IEventMapper eventMapper;
    private final IImageService imageService;

    @Autowired
    public EventServiceImpl(
            IEventRepository eventRepository,
            IEventMapper eventMapper,
            IImageService imageService
    ) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.imageService = imageService;
    }

    public ServiceResponse<Boolean> createEvent(CreateEventRequest createEventRequest) {

        var response = new ServiceResponse<Boolean>();

        if (createEventRequest == null){
            response.setMessage("Request is null");
            return response;
        }

        Event event = eventMapper.createEventRequestToEvent(createEventRequest);

        try{
            eventRepository.save(event);
            response.setSuccess(true);
        } catch (Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            return response;
        }
        return response;
    }

    public ServiceResponse<Page<String>> getEventTitleByMatchingIdOrTitle(SearchEventsRequest request)
    {
        var response = new ServiceResponse<Page<String>>();

        if (request == null){
            response.setMessage("Request is null");
            return response;
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());

        Page<String> eventTitlePage =  eventRepository.findEventTitleByIdOrTitleContainingIgnoreCase(request.getKeyword(), pageable);

        response.setData(eventTitlePage);
        response.setSuccess(true);

        return response;
    }
}
