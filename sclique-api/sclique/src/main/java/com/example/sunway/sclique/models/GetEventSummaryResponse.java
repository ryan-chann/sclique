package com.example.sunway.sclique.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetEventSummaryResponse {
    private String title;
    private String venue;
    private List<CreateEventRequest.EventFeeDto> eventFees;
    private List<CreateEventRequest.EventSessionDto> eventSessions;
    private String imageDataBase64;
    private String mimeType;
}
