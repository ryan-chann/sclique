package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.event.*;
import com.example.sunway.sclique.models.ServiceResponse;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface IEventService {

    ServiceResponse<Boolean> createEvent(CreateEventRequest createEventRequest, MultipartFile eventAdvertisementImage);

    public ServiceResponse<Page<GetEventTitleResponse>> getEventTitleByMatchingIdOrTitle(SearchEventsRequest searchEventsRequest);

    ServiceResponse<Page<GetEventSummaryResponse>> getEventSummaryByTitleContainingIgnoreCase(SearchEventsRequest request);

    public ServiceResponse<GetEventProfileResponse> getEventProfileById(GetEventProfileRequest getEventProfileRequest);
}
