package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.models.CreateEventRequest;
import com.example.sunway.sclique.models.SearchEventsResponse;
import com.example.sunway.sclique.entities.EventFee;
import com.example.sunway.sclique.repositories.IEventRepository;

import com.example.sunway.sclique.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sunway.sclique.entities.Event;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {
    private final IEventRepository eventRepository;

    @Autowired
    public EventService(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public boolean createEvent(CreateEventRequest createEventRequest)
    {
        Event savedEvent = eventRepository.save(mapCreateEventRequestToEvent(createEventRequest));

        return savedEvent.getId() != null;
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

    private static Event mapCreateEventRequestToEvent(CreateEventRequest createEventRequest) {
        Event event = new Event();
        event.setTitle(createEventRequest.getTitle());
        event.setDescription(createEventRequest.getDescription());
        event.setVenue(createEventRequest.getVenue());
        event.setDurationInMinutes(createEventRequest.getDurationInMinutes());

        if(createEventRequest.getEventFee() != null) {
            event.setEventFee(
                    createEventRequest.getEventFee().stream().map(eventFeeDto -> {
                        EventFee eventFee = new EventFee();
                        eventFee.setType(eventFeeDto.getType());
                        eventFee.setPrice(eventFeeDto.getPrice());
                        eventFee.setEvent(event);
                        return eventFee;
                    }).collect(Collectors.toList())
            );
        }
        return event;
    }
}
