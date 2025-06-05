package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.CreateEventRequest;
import com.example.sunway.sclique.models.SearchEventsRequest;
import com.example.sunway.sclique.services.IEventService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.sunway.sclique.utils.ResponseUtil.handleServiceResponse;

@Validated
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

    @PostMapping()
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest createEventRequest) {
        var serviceResponse = eventService.createEvent(createEventRequest);
        return handleServiceResponse(serviceResponse, HttpStatus.CREATED);

    }
}
