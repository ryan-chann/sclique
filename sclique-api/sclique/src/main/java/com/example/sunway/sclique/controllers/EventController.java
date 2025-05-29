package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.CreateEventRequest;
import com.example.sunway.sclique.models.SearchEventsResponse;
import com.example.sunway.sclique.models.ServiceResponse;
import com.example.sunway.sclique.services.IEventService;
import com.example.sunway.sclique.services.implementation.EventServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final IEventService eventService;

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/search")
    public List<SearchEventsResponse> searchEvents(@RequestParam String keyword) {
        return eventService.getEventByMatchingIdOrTitle(keyword);
    }

    @PostMapping()
    public ResponseEntity<?> createEvent(@RequestBody @Valid CreateEventRequest createEventRequest) {

        var serviceResponse  = eventService.createEvent(createEventRequest);

        if (serviceResponse.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceResponse.isSuccess());
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(serviceResponse.getMessage());
        }
    }


}
