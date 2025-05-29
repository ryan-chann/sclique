package com.example.sunway.sclique.mapper;

import com.example.sunway.sclique.entities.Event;
import com.example.sunway.sclique.models.CreateEventRequest;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = IEventFeeMapper.class)
public interface IEventMapper {

    @Mapping(source = "eventFees", target = "eventFee")
    Event createEventRequestToEvent(CreateEventRequest createEventRequest);

    @AfterMapping
    default void linkEventToEventFees(CreateEventRequest request, @MappingTarget Event event) {
        if (event.getEventFee() != null) {
            event.getEventFee().forEach(fee -> fee.setEvent(event));
        }
    }
}
