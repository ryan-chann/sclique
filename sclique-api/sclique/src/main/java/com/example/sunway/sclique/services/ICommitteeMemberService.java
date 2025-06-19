package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.ServiceResponse;
import com.example.sunway.sclique.models.committeeMember.CommitteeMemberJoinOrganisationRequest;
import com.example.sunway.sclique.models.committeeMember.CreateCommitteeMemberRequest;

public interface ICommitteeMemberService {
    public ServiceResponse<Boolean> createCommitteeMember(CreateCommitteeMemberRequest createCommitteeMemberRequest);

    ServiceResponse<Boolean> joinOrganisation(CommitteeMemberJoinOrganisationRequest committeeMemberJoinOrganisationRequest);
}
