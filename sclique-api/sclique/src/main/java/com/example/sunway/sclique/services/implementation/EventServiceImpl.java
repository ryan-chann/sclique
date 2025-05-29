package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.mapper.IEventMapper;
import com.example.sunway.sclique.models.CreateEventRequest;
import com.example.sunway.sclique.models.SearchEventsResponse;
import com.example.sunway.sclique.repositories.IEventRepository;

import com.example.sunway.sclique.services.IEventService;
import com.example.sunway.sclique.services.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sunway.sclique.entities.Event;
import com.example.sunway.sclique.models.ServiceResponse;

import java.util.List;
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
            Event savedEvent = eventRepository.save(event);
            response.setSuccess(true);
        } catch (Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            return response;
        }
        return response;
    }

    public List<SearchEventsResponse> getEventByMatchingIdOrTitle(String keyword)
    {
        List<Event> events =  eventRepository.searchByIdOrTitle(keyword);

        return events.stream()
                .map(event -> {
                    SearchEventsResponse response = new SearchEventsResponse();
                    response.setTitle(event.getTitle());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
