package com.example.sunway.sclique.mapper;

import com.example.sunway.sclique.entities.EventFee;
import com.example.sunway.sclique.models.CreateEventRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEventFeeMapper {
    EventFee eventFeeDtoToEventFee(CreateEventRequest.EventFeeDto dto);
}
