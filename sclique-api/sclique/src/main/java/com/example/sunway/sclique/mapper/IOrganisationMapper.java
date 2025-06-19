package com.example.sunway.sclique.mapper;

import com.example.sunway.sclique.entities.Organisation;
import com.example.sunway.sclique.models.organisation.CreateOrganisationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IEventFeeMapper.class)
public interface IOrganisationMapper {

    Organisation createOrganisationRequestToOrganisation(CreateOrganisationRequest createOrganisationRequest);
}
