package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.event.CreateEventRequest;
import com.example.sunway.sclique.models.event.SearchEventsRequest;
import com.example.sunway.sclique.services.IEventService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.sunway.sclique.utils.ResponseUtil.handleServiceResponse;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    private final IEventService eventService;

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/search/titles")
    public ResponseEntity<?> searchEvents(@ModelAttribute @Valid SearchEventsRequest searchEventsRequest) {
        var serviceResponse = eventService.getEventTitleByMatchingIdOrTitle(searchEventsRequest);

        return handleServiceResponse(serviceResponse, HttpStatus.OK);
    }

    @GetMapping("/search/listing")
    public ResponseEntity<?> searchEventListing(@ModelAttribute @Valid SearchEventsRequest searchEventsRequest) {
        var serviceResponse = eventService.getEventSummaryByTitleContainingIgnoreCase(searchEventsRequest);

        return handleServiceResponse(serviceResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createEvent(
        @RequestPart("request") CreateEventRequest createEventRequest,
        @RequestPart("eventAdvertisementImage") @Valid MultipartFile eventAdvertisementImage
    ) {
        var serviceResponse = eventService.createEvent(createEventRequest, eventAdvertisementImage);
        return handleServiceResponse(serviceResponse, HttpStatus.CREATED);

    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@ModelAttribute String eventId) {
        var serviceResponse = eventService.getEventProfileById(eventId);
        return handleServiceResponse(serviceResponse, HttpStatus.OK);
    }
}
