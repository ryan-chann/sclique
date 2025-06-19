package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.CommitteeMember;
import com.example.sunway.sclique.entities.Organisation;
import com.example.sunway.sclique.entities.OrganisationCommitteeMember;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import com.example.sunway.sclique.mapper.ICommitteeMemberMapper;
import com.example.sunway.sclique.models.ServiceResponse;
import com.example.sunway.sclique.models.committeeMember.CommitteeMemberJoinOrganisationRequest;
import com.example.sunway.sclique.models.committeeMember.CreateCommitteeMemberRequest;
import com.example.sunway.sclique.models.image.CreateImageRequest;
import com.example.sunway.sclique.repositories.ICommitteeMemberRepository;
import com.example.sunway.sclique.repositories.IImageRepository;
import com.example.sunway.sclique.repositories.IOrganisationCommitteeMemberRepository;
import com.example.sunway.sclique.repositories.IOrganisationRepository;
import com.example.sunway.sclique.services.ICommitteeMemberService;
import com.example.sunway.sclique.services.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommitteeMemberServiceImpl implements ICommitteeMemberService {
    private final ICommitteeMemberRepository committeeMemberRepository;
    private final ICommitteeMemberMapper committeeMemberMapper;
    private final IOrganisationRepository organisationRepository;
    private final IOrganisationCommitteeMemberRepository organisationCommitteeMemberRepository;
    private final IImageService imageService;

    @Autowired
    public CommitteeMemberServiceImpl(
            ICommitteeMemberRepository committeeMemberRepository,
            ICommitteeMemberMapper committeeMemberMapper,
            IOrganisationRepository organisationRepository,
            IOrganisationCommitteeMemberRepository organisationCommitteeMemberRepository,
            IImageService imageService
    ) {
        this.committeeMemberRepository = committeeMemberRepository;
        this.committeeMemberMapper = committeeMemberMapper;
        this.organisationRepository = organisationRepository;
        this.organisationCommitteeMemberRepository = organisationCommitteeMemberRepository;
        this.imageService = imageService;
    }

    public ServiceResponse<Boolean> createCommitteeMember(CreateCommitteeMemberRequest createCommitteeMemberRequest, MultipartFile memberFaceImage) {
        var response = new ServiceResponse<Boolean>();

        if (createCommitteeMemberRequest == null){
            response.setErrorMessage("Request is null");
            return response;
        }

        CommitteeMember committeeMember = committeeMemberMapper.createCommitteeMemberRequestToCommitteeMember(createCommitteeMemberRequest);

        CommitteeMember savedCommitteeMember = committeeMemberRepository.save(committeeMember);

        CreateImageRequest createImageRequest = new CreateImageRequest(
            savedCommitteeMember.getId().toString(),
            EntityType.COMMITTEE_MEMBER.getId(),
            ImageType.COMMITTEE_MEMBER_FACE_IMAGE.getId()
        );

        try {
            imageService.saveImage(memberFaceImage, createImageRequest);
        } catch (IOException ioException){
            response.setErrorMessage(ioException.getMessage());
            return response;
        }

        response.setSuccess(true);
        response.setData(Boolean.TRUE);

        return response;
    }


    public ServiceResponse<Boolean> joinOrganisation(CommitteeMemberJoinOrganisationRequest committeeMemberJoinOrganisationRequest) {
        var response = new ServiceResponse<Boolean>();

        Optional<Organisation> organisationOptional = organisationRepository.findById(UUID.fromString(committeeMemberJoinOrganisationRequest.getOrganisationId()));
        if (organisationOptional.isEmpty()) {
            response.setErrorMessage("Organisation not found");
            return response;
        }

        Optional<CommitteeMember> memberOptional = committeeMemberRepository.findById(UUID.fromString(committeeMemberJoinOrganisationRequest.getCommitteeMemberId()));
        if (memberOptional.isEmpty()) {
            response.setErrorMessage("Committee member not found");
            return response;
        }

        Organisation organisation = organisationOptional.get();
        CommitteeMember committeeMember = memberOptional.get();

        OrganisationCommitteeMember organisationCommitteeMember = new OrganisationCommitteeMember();
        organisationCommitteeMember.setOrganisation(organisation);
        organisationCommitteeMember.setCommitteeMember(committeeMember);
        organisationCommitteeMember.setPosition(committeeMemberJoinOrganisationRequest.getPosition());

        if (committeeMemberJoinOrganisationRequest.getManagerId() != null) {
            Optional<OrganisationCommitteeMember> managerOpt = organisationCommitteeMemberRepository.findById(committeeMemberJoinOrganisationRequest.getManagerId());

            if (managerOpt.isEmpty()) {
                response.setErrorMessage("Manager not found");
                return response;
            }

            organisationCommitteeMember.setManager(managerOpt.get());
        }

        organisationCommitteeMemberRepository.save(organisationCommitteeMember);

        response.setSuccess(true);
        response.setData(Boolean.TRUE);

        return response;
    }

}
