package com.example.sunway.sclique.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateEventRequest {
    private String title;
    private String description;
    private String venue;
    private int durationInMinutes;
    private List<EventFeeDto> eventFee;

    @Getter
    @Setter
    public static class EventFeeDto {
        private String type;
        private double price;
    }
}
