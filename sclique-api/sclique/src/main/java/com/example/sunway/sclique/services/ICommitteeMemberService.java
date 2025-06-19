package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.ServiceResponse;
import com.example.sunway.sclique.models.committeeMember.CommitteeMemberJoinOrganisationRequest;
import com.example.sunway.sclique.models.committeeMember.CreateCommitteeMemberRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ICommitteeMemberService {
    ServiceResponse<Boolean> createCommitteeMember(CreateCommitteeMemberRequest createCommitteeMemberRequest, MultipartFile memberFaceImage);

    ServiceResponse<Boolean> joinOrganisation(CommitteeMemberJoinOrganisationRequest committeeMemberJoinOrganisationRequest);
}
