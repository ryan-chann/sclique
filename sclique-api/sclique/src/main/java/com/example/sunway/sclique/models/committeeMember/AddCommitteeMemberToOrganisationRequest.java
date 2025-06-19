package com.example.sunway.sclique.models.committeeMember;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCommitteeMemberToOrganisationRequest {
    private String committeeMemberId;
    private String organisationId;
    private String position;
    private String managerId;
}
