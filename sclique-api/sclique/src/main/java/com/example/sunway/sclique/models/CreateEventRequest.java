package com.example.sunway.sclique.models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateEventRequest {
    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Venue is required")
    private String venue;

    @NotNull(message = "DurationInMinutes is required")
    private int durationInMinutes;

    @NotNull(message = "ParticipationLink is required")
    private String participationLink;

    @NotNull(message = "EventFees is required")
    private List<EventFeeDto> eventFees;

    @Getter
    @Setter
    public static class EventFeeDto {
        @NotNull(message = "EventFee Type is required")
        private String type;

        @NotNull(message = "EventFee Type is required")
        private double price;
    }
}
