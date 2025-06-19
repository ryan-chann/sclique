package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.CommitteeMember;
import com.example.sunway.sclique.entities.Organisation;
import com.example.sunway.sclique.entities.OrganisationCommitteeMember;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import com.example.sunway.sclique.mapper.IOrganisationMapper;
import com.example.sunway.sclique.models.*;
import com.example.sunway.sclique.models.image.CreateImageRequest;
import com.example.sunway.sclique.models.image.GetImageByEntityIdResponse;
import com.example.sunway.sclique.models.organisation.*;
import com.example.sunway.sclique.repositories.ICommitteeMemberRepository;
import com.example.sunway.sclique.repositories.IOrganisationCommitteeMemberRepository;
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
import java.util.*;

@Service
public class OrganisationServiceImpl implements IOrganisationService {
    private final IOrganisationRepository organisationRepository;
    private final IOrganisationMapper organisationMapper;
    private final IImageService imageService;
    private final ICommitteeMemberRepository committeeMemberRepository;
    private final IOrganisationCommitteeMemberRepository organisationCommitteeMemberRepository;

    @Autowired
    public OrganisationServiceImpl(
            IOrganisationRepository organisationRepository,
            IOrganisationMapper organisationMapper,
            IImageService imageService,
            ICommitteeMemberRepository committeeMemberRepository,
            IOrganisationCommitteeMemberRepository organisationCommitteeMemberRepository
    ) {
        this.organisationRepository = organisationRepository;
        this.organisationMapper = organisationMapper;
        this.imageService = imageService;
        this.committeeMemberRepository = committeeMemberRepository;
        this.organisationCommitteeMemberRepository = organisationCommitteeMemberRepository;
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

        Page<Object[]> repositoryResponsePage = organisationRepository.findOrganisationNameByIdOrTitleContainingIgnoreCase(searchOrganisationsRequest.getQuery(), pageable);

        Page<GetOrganisationNameResponse> serviceResponsePage = repositoryResponsePage.map( object -> {
           UUID organisationId = (UUID) object[0];
           String organisationName = (String) object[1];

           return new GetOrganisationNameResponse(
               organisationId.toString(),
               organisationName
           );
        });

        response.setData(serviceResponsePage);
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
    public ServiceResponse<GetOrganisationProfileResponse> getOrganisationProfile(GetOrganisationProfileRequest getOrganisationProfileRequest) {
        var response = new ServiceResponse<GetOrganisationProfileResponse>();

        Optional<Organisation> organisationOptional = organisationRepository.findById(UUID.fromString(getOrganisationProfileRequest.getOrganisationId()));

        if (organisationOptional.isEmpty()) {
            response.setErrorMessage("Organisation not found");
            return response;
        }

        Organisation organisation = organisationOptional.get();

        List<OrganisationCommitteeMember> committeeMembers = organisationCommitteeMemberRepository.findByOrganisationId(organisation.getId());

        List<GetOrganisationProfileResponse.CommitteeMemberDto> committeeMemberDtos = new ArrayList<>();

        for (OrganisationCommitteeMember committeeMember : committeeMembers) {
            CommitteeMember member = committeeMember.getCommitteeMember();
            GetOrganisationProfileResponse.CommitteeMemberDto dto = new GetOrganisationProfileResponse.CommitteeMemberDto();

            dto.setMemberId(committeeMember.getId());
            dto.setName(member.getName());
            dto.setEmail(member.getEmail());
            dto.setPhoneNumber(member.getPhoneNumber());
            dto.setPosition(committeeMember.getPosition());
            dto.setManagerId(committeeMember.getManager() != null ? committeeMember.getManager().getId() : null);

            ServiceResponse<List<GetImageByEntityIdResponse>> imageResponse =
                    imageService.getImageByEntityId(member.getId().toString());

            if (imageResponse.isSuccess() && !imageResponse.getData().isEmpty()) {
                imageResponse.getData().stream().findFirst().ifPresent(img -> {
                    GetOrganisationProfileResponse.ImageDto imageDto = new GetOrganisationProfileResponse.ImageDto();
                    imageDto.setMimeType(img.getMimeType());
                    imageDto.setImageDataBase64(img.getImageDataBase64());
                    dto.setImage(imageDto);
                });

                imageResponse.getData().stream()
                    .filter(img -> img.getImageType() == ImageType.COMMITTEE_MEMBER_FACE_IMAGE)
                    .findFirst()
                    .ifPresent(avatarImg -> {
                        GetOrganisationProfileResponse.ImageDto avatarDto = new GetOrganisationProfileResponse.ImageDto();
                        avatarDto.setMimeType(avatarImg.getMimeType());
                        avatarDto.setImageDataBase64(avatarImg.getImageDataBase64());
                        dto.setAvatar(avatarDto);
                });
            }

            committeeMemberDtos.add(dto);
        }

        ServiceResponse<List<GetImageByEntityIdResponse>> organisationImageResponse = imageService.getImageByEntityId(organisation.getId().toString());

        GetOrganisationProfileResponse.ImageDto organisationCoverImage = null;
        GetOrganisationProfileResponse.ImageDto organisationProfileImage = null;

        if (organisationImageResponse.isSuccess() && !organisationImageResponse.getData().isEmpty()) {
            Optional<GetImageByEntityIdResponse> coverOpt = organisationImageResponse.getData().stream()
                    .filter(img -> img.getImageType() == ImageType.ORGANISATION_COVER_IMAGE)
                    .findFirst();

            if (coverOpt.isPresent()) {
                organisationCoverImage = new GetOrganisationProfileResponse.ImageDto();
                organisationCoverImage.setMimeType(coverOpt.get().getMimeType());
                organisationCoverImage.setImageDataBase64(coverOpt.get().getImageDataBase64());
            }

            Optional<GetImageByEntityIdResponse> profileOpt = organisationImageResponse.getData().stream()
                .filter(img -> img.getImageType() == ImageType.ORGANISATION_PROFILE_IMAGE)
                .findFirst();

            if (profileOpt.isPresent()) {
                organisationProfileImage = new GetOrganisationProfileResponse.ImageDto();
                organisationProfileImage.setMimeType(profileOpt.get().getMimeType());
                organisationProfileImage.setImageDataBase64(profileOpt.get().getImageDataBase64());
            }
        }

        GetOrganisationProfileResponse profile = new GetOrganisationProfileResponse();
        profile.setOrganisationId(organisation.getId().toString());
        profile.setName(organisation.getName());
        profile.setDescription(organisation.getDescription());
        profile.setCommitteeMembers(committeeMemberDtos);
        profile.setCoverImage(organisationCoverImage);
        profile.setProfileImage(organisationProfileImage);

        response.setData(profile);
        response.setSuccess(true);
        return response;
    }

}
