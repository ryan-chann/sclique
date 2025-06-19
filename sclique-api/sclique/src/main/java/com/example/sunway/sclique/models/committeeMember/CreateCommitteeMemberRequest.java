package com.example.sunway.sclique.models.committeeMember;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommitteeMemberRequest {
    private String name;
    private String email;
    private String phoneNumber;
}
