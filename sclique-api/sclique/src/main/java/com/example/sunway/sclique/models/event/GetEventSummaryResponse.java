package com.example.sunway.sclique.models.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetEventSummaryResponse {
    private String id;
    private String title;
    private String venue;
    private List<CreateEventRequest.EventFeeDto> eventFees;
    private List<CreateEventRequest.EventSessionDto> eventSessions;
    private String imageDataBase64;
    private String mimeType;
}
