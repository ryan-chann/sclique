package com.example.sunway.sclique.mapper;

import com.example.sunway.sclique.entities.EventSession;
import com.example.sunway.sclique.models.CreateEventRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEventSessionMapper {
    EventSession eventSessionDtoToEventSession(CreateEventRequest.EventSessionDto dto);
}