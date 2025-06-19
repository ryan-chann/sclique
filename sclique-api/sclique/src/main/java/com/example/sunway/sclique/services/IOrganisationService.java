package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.*;
import com.example.sunway.sclique.models.organisation.CreateOrganisationRequest;
import com.example.sunway.sclique.models.organisation.GetOrganisationNameAndImageResponse;
import com.example.sunway.sclique.models.organisation.GetOrganisationNameResponse;
import com.example.sunway.sclique.models.organisation.SearchOrganisationsRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface IOrganisationService {
    ServiceResponse<Boolean> createOrganisation(CreateOrganisationRequest createOrganisationRequest, MultipartFile  organisationProfileImage, MultipartFile organisationCoverImage);

    ServiceResponse<Page<GetOrganisationNameResponse>> getOrganisationNameByMatchingIdOrTitle(SearchOrganisationsRequest searchOrganisationsRequest);

    ServiceResponse<Page<GetOrganisationNameAndImageResponse>> getOrganisationNameAndImageByMatchingTitle(SearchOrganisationsRequest searchOrganisationsRequest);
}
