package com.example.sunway.sclique.models.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetEventProfileResponse {
    private String id;
    private String name;
    private String venue;
    private int durationInMinutes;
    private String participationLink;
    private OrganiserDto organiser;
    private List<EventFeeDto> eventFees;
    private List<EventSessionDto> eventSessions;
    private ImageDto eventImage;

    @Getter
    @Setter
    public static class OrganiserDto {
        private ImageDto organiserImage;
        private String name;
    }

    @Getter
    @Setter
    public static class EventFeeDto {
        private String type;
        private double price;
    }

    @Getter
    @Setter
    public static class EventSessionDto {
        private LocalDateTime session;
    }

    @Getter
    @Setter
    public static class ImageDto {
        private String mimeType;
        private String imageDataBase64;
    }
}
