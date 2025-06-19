package com.example.sunway.sclique.models.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateEventRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Venue is required")
    private String venue;

    @NotNull(message = "DurationInMinutes is required")
    @Positive(message = "DurationInMinutes must be a positive integer")
    private int durationInMinutes;

    @NotBlank(message = "ParticipationLink is required")
    private String participationLink;

    @NotNull(message = "EventFees is required")
    private List<EventFeeDto> eventFees;

    @NotNull(message = "EventSessions is required")
    private List<EventSessionDto> eventSessions;

    @NotNull(message = "OrganiserId is required")
    private String organiserId;

    @Getter
    @Setter
    public static class EventFeeDto {
        @NotBlank(message = "EventFee Type is required")
        private String type;

        @NotNull(message = "EventFee Price is required")
        @Positive(message = "Price must be a positive integer")
        private double price;
    }

    @Getter
    @Setter
    public static class EventSessionDto {
        @NotNull(message = "EventSession Session is required")
        private LocalDateTime session;
    }
}
