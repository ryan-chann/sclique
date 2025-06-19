package com.example.sunway.sclique.models.committeeMember;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommitteeMemberJoinOrganisationRequest {
    @NotNull
    private String organisationId;

    @NotNull
    private String committeeMemberId;

    @NotBlank
    private String position;

    private Long managerId;
}
