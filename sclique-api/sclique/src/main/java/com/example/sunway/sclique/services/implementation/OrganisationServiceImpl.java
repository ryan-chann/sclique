package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.Organisation;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import com.example.sunway.sclique.mapper.IOrganisationMapper;
import com.example.sunway.sclique.models.*;
import com.example.sunway.sclique.models.image.CreateImageRequest;
import com.example.sunway.sclique.models.organisation.*;
import com.example.sunway.sclique.repositories.IOrganisationRepository;
import com.example.sunway.sclique.services.IImageService;
import com.example.sunway.sclique.services.IOrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class OrganisationServiceImpl implements IOrganisationService {
    private final IOrganisationRepository organisationRepository;
    private final IOrganisationMapper organisationMapper;
    private final IImageService imageService;

    @Autowired
    public OrganisationServiceImpl(
            IOrganisationRepository organisationRepository,
            IOrganisationMapper organisationMapper,
            IImageService imageService
    ) {
        this.organisationRepository = organisationRepository;
        this.organisationMapper = organisationMapper;
        this.imageService = imageService;
    }

    public ServiceResponse<Boolean> createOrganisation(CreateOrganisationRequest createOrganisationRequest, MultipartFile organisationProfileImage, MultipartFile organisationCoverImage) {
        var response = new ServiceResponse<Boolean>();

        if(createOrganisationRequest == null) {
            response.setErrorMessage("Request is null");
            return response;
        }

        Organisation organisation = organisationMapper.createOrganisationRequestToOrganisation(createOrganisationRequest);

        Organisation savedOrganisation = organisationRepository.save(organisation);

        CreateImageRequest createOrganisationProfileImageRequest = new CreateImageRequest(
            savedOrganisation.getId().toString(),
            EntityType.ORGANISATION.getId(),
            ImageType.ORGANISATION_PROFILE_IMAGE.getId()
        );

        CreateImageRequest createOrganisationCoverImageRequest = new CreateImageRequest(
            savedOrganisation.getId().toString(),
            EntityType.ORGANISATION.getId(),
            ImageType.ORGANISATION_COVER_IMAGE.getId()
        );

        try {
            imageService.saveImage(organisationProfileImage, createOrganisationProfileImageRequest);
            imageService.saveImage(organisationCoverImage, createOrganisationCoverImageRequest);
        }
        catch (IOException ioException) {
            response.setErrorMessage(ioException.getMessage());
            return response;
        }

        response.setSuccess(true);
        response.setData(Boolean.TRUE);

        return response;
    }

    public ServiceResponse<Page<GetOrganisationNameResponse>> getOrganisationNameByMatchingIdOrTitle(SearchOrganisationsRequest searchOrganisationsRequest) {
        var response = new ServiceResponse<Page<GetOrganisationNameResponse>>();

        if(searchOrganisationsRequest == null) {
            response.setErrorMessage("Request is null");
            return response;
        }

        Pageable pageable = PageRequest.of(searchOrganisationsRequest.getPage(), searchOrganisationsRequest.getPageSize());

        Page<GetOrganisationNameResponse> organisationNamePage = organisationRepository.findOrganisationNameByIdOrTitleContainingIgnoreCase(searchOrganisationsRequest.getQuery(), pageable);

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
            UUID id = (UUID) row[0];
            String name = (String) row[1];
            byte[] imageBytes = (byte[]) row[2];
            String mimeType = (String) row[3];

            return new GetOrganisationNameAndImageResponse(
                id.toString(),
                name,
                imageBytes != null ? Base64.getEncoder().encodeToString(imageBytes) : null,
                mimeType
            );
        });

        response.setData(serviceResponsePage);
        response.setSuccess(true);

        return response;
    }

    @Transactional
    public ServiceResponse<GetOrganisationProfileResponse> getOrganisationProfile(String id) {
        var response = new ServiceResponse<GetOrganisationProfileResponse>();


        return response;
    }
}
