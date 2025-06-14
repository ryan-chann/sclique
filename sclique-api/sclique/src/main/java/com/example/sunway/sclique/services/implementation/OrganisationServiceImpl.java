package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.Organisation;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import com.example.sunway.sclique.mapper.IOrganisationMapper;
import com.example.sunway.sclique.models.CreateOrganisationRequest;
import com.example.sunway.sclique.models.GetOrganisationNameAndImageResponse;
import com.example.sunway.sclique.models.SearchOrganisationsRequest;
import com.example.sunway.sclique.models.ServiceResponse;
import com.example.sunway.sclique.repositories.IOrganisationRepository;
import com.example.sunway.sclique.services.IOrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Service
public class OrganisationServiceImpl implements IOrganisationService {
    private final IOrganisationRepository organisationRepository;
    private final IOrganisationMapper organisationMapper;

    @Autowired
    public OrganisationServiceImpl(IOrganisationRepository organisationRepository, IOrganisationMapper organisationMapper) {
        this.organisationRepository = organisationRepository;
        this.organisationMapper = organisationMapper;
    }

    public ServiceResponse<Boolean> createOrganisation(CreateOrganisationRequest createOrganisationRequest) {
        var response = new ServiceResponse<Boolean>();

        if(createOrganisationRequest == null) {
            response.setErrorMessage("Reques is null");
            return response;
        }

        Organisation organisation = organisationMapper.createOrganisationRequestToOrganisation(createOrganisationRequest);

        organisationRepository.save(organisation);

        response.setSuccess(true);
        response.setData(Boolean.TRUE);

        return response;
    }

    public ServiceResponse<Page<String>> getOrganisationNameByMatchingIdOrTitle(SearchOrganisationsRequest searchOrganisationsRequest) {
        var response = new ServiceResponse<Page<String>>();

        if(searchOrganisationsRequest == null) {
            response.setErrorMessage("Request is null");
            return response;
        }

        Pageable pageable = PageRequest.of(searchOrganisationsRequest.getPage(), searchOrganisationsRequest.getPageSize());

        Page<String> organisationNamePage = organisationRepository.findOrganisationNameByIdOrTitleContainingIgnoreCase(searchOrganisationsRequest.getQuery(), pageable);

        response.setData(organisationNamePage);
        response.setSuccess(true);

        return response;
    }


    @Transactional
    public ServiceResponse<Page<GetOrganisationNameAndImageResponse>> getOrganisationNameAndImageByMatchingTitle(SearchOrganisationsRequest searchOrganisationsRequest) {
        var response = new ServiceResponse<Page<GetOrganisationNameAndImageResponse>>();

        if (searchOrganisationsRequest == null) {
            response.setErrorMessage("Request is null");
            return response;
        }

        Pageable pageable = PageRequest.of(
                searchOrganisationsRequest.getPage(),
                searchOrganisationsRequest.getPageSize()
        );

        Page<Object[]> repositoryResponsePage = organisationRepository.findOrganisationNameImageByTitleContainingIgnoreCase(
                searchOrganisationsRequest.getQuery(),
                EntityType.ORGANISATION,
                ImageType.ORGANISATION_PROFILE_IMAGE,
                pageable
        );

        Page<GetOrganisationNameAndImageResponse> serviceResponsePage = repositoryResponsePage.map(row -> {
            String name = (String) row[0];
            byte[] imageBytes = (byte[]) row[1];
            String mimeType = (String) row[2];

            return new GetOrganisationNameAndImageResponse(
                name,
                imageBytes != null ? Base64.getEncoder().encodeToString(imageBytes) : null,
                mimeType
            );
        });

        response.setData(serviceResponsePage);
        response.setSuccess(true);

        return response;
    }
}
