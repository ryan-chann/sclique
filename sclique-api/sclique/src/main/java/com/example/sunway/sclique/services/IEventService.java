package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.CreateEventRequest;
import com.example.sunway.sclique.models.SearchEventsResponse;
import com.example.sunway.sclique.models.ServiceResponse;

import java.util.List;

public interface IEventService {

    ServiceResponse<Boolean> createEvent(CreateEventRequest createEventRequest);

    List<SearchEventsResponse> getEventByMatchingIdOrTitle(String keyword);
}
