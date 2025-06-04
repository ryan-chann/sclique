package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.CreateEventRequest;
import com.example.sunway.sclique.models.SearchEventsRequest;
import com.example.sunway.sclique.models.ServiceResponse;

import org.springframework.data.domain.Page;

public interface IEventService {

    ServiceResponse<Boolean> createEvent(CreateEventRequest createEventRequest);

    ServiceResponse<Page<String>> getEventTitleByMatchingIdOrTitle(SearchEventsRequest request);
}
