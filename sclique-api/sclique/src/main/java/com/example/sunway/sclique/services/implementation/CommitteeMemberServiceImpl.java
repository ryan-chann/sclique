package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.CommitteeMember;
import com.example.sunway.sclique.entities.Organisation;
import com.example.sunway.sclique.entities.OrganisationCommitteeMember;
import com.example.sunway.sclique.mapper.ICommitteeMemberMapper;
import com.example.sunway.sclique.models.ServiceResponse;
import com.example.sunway.sclique.models.committeeMember.CommitteeMemberJoinOrganisationRequest;
import com.example.sunway.sclique.models.committeeMember.CreateCommitteeMemberRequest;
import com.example.sunway.sclique.repositories.ICommitteeMemberRepository;
import com.example.sunway.sclique.repositories.IOrganisationCommitteeMemberRepository;
import com.example.sunway.sclique.repositories.IOrganisationRepository;
import com.example.sunway.sclique.services.ICommitteeMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommitteeMemberServiceImpl implements ICommitteeMemberService {
    private final ICommitteeMemberRepository committeeMemberRepository;
    private final ICommitteeMemberMapper committeeMemberMapper;
    private final IOrganisationRepository organisationRepository;
    private final IOrganisationCommitteeMemberRepository organisationCommitteeMemberRepository;

    @Autowired
    public CommitteeMemberServiceImpl(
            ICommitteeMemberRepository committeeMemberRepository,
            ICommitteeMemberMapper committeeMemberMapper,
            IOrganisationRepository organisationRepository,
            IOrganisationCommitteeMemberRepository organisationCommitteeMemberRepository
    ) {
        this.committeeMemberRepository = committeeMemberRepository;
        this.committeeMemberMapper = committeeMemberMapper;
        this.organisationRepository = organisationRepository;
        this.organisationCommitteeMemberRepository = organisationCommitteeMemberRepository;
    }

    public ServiceResponse<Boolean> createCommitteeMember(CreateCommitteeMemberRequest createCommitteeMemberRequest) {
        var response = new ServiceResponse<Boolean>();

        if (createCommitteeMemberRequest == null){
            response.setErrorMessage("Request is null");
            return response;
        }

        CommitteeMember committeeMember = committeeMemberMapper.createCommitteeMemberRequestToCommitteeMember(createCommitteeMemberRequest);

        committeeMemberRepository.save(committeeMember);

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
