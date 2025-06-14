package com.example.sunway.sclique.mapper;

import com.example.sunway.sclique.entities.Event;
import com.example.sunway.sclique.models.CreateEventRequest;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = "spring",
    uses = {IEventFeeMapper.class, IEventSessionMapper.class}
)
public interface IEventMapper {
    Event createEventRequestToEvent(CreateEventRequest createEventRequest);

    @AfterMapping
    default void linkEventToEventFees(CreateEventRequest request, @MappingTarget Event event) {
        if (event.getEventFees() != null) {
            event.getEventFees().forEach(fee -> fee.setEvent(event));
        }
    }

    @AfterMapping
    default void linkEventToEventSession(CreateEventRequest request, @MappingTarget Event event) {
        if (event.getEventSessions() != null) {
            event.getEventSessions().forEach(session -> session.setEvent(event));
        }
    }
}
