package com.example.sunway.sclique.controller;

import com.example.sunway.sclique.model.CreateEventRequest;
import com.example.sunway.sclique.model.SearchEventsResponse;
import com.example.sunway.sclique.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/search")
    public List<SearchEventsResponse> searchEvents(@RequestParam String keyword) {
        return eventService.getEventByMatchingIdOrTitle(keyword);
    }

    @PostMapping
    public ResponseEntity<Boolean> createEvent(@RequestBody CreateEventRequest createEventRequest) {
        boolean isSuccess = eventService.createEvent(createEventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(isSuccess);
    }
}
