package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.CreateEventRequest;
import com.example.sunway.sclique.models.GetEventSummaryResponse;
import com.example.sunway.sclique.models.SearchEventsRequest;
import com.example.sunway.sclique.models.ServiceResponse;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface IEventService {

    ServiceResponse<Boolean> createEvent(CreateEventRequest createEventRequest, MultipartFile eventAdvertisementImage);

    ServiceResponse<Page<String>> getEventTitleByMatchingIdOrTitle(SearchEventsRequest request);

    ServiceResponse<Page<GetEventSummaryResponse>> getEventSummaryByTitleContainingIgnoreCase(SearchEventsRequest request);
}
