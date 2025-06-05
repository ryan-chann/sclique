package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.CreateOrganisationRequest;
import com.example.sunway.sclique.models.SearchOrganisationsRequest;
import com.example.sunway.sclique.models.ServiceResponse;
import org.springframework.data.domain.Page;

public interface IOrganisationService {
    ServiceResponse<Boolean> createOrganisation(CreateOrganisationRequest createOrganisationRequest);

    ServiceResponse<Page<String>> getOrganisationNameByMatchingIdOrTitle(SearchOrganisationsRequest searchOrganisationsRequest);
}
