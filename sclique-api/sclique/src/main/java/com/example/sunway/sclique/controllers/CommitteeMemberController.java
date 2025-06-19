package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.committeeMember.CommitteeMemberJoinOrganisationRequest;
import com.example.sunway.sclique.models.committeeMember.CreateCommitteeMemberRequest;
import com.example.sunway.sclique.services.ICommitteeMemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.sunway.sclique.utils.ResponseUtil.handleServiceResponse;

@RestController
@RequestMapping("/api/v1/committee-member")
public class CommitteeMemberController {
    private final ICommitteeMemberService committeeMemberService;

    @Autowired
    public CommitteeMemberController(ICommitteeMemberService committeeMemberService) {
        this.committeeMemberService = committeeMemberService;
    }

    @PostMapping()
    public ResponseEntity<?> createCommitteeMember(@RequestBody CreateCommitteeMemberRequest createCommitteeMemberRequestteeMember) {
        var serviceResponse = committeeMemberService.createCommitteeMember(createCommitteeMemberRequestteeMember);
        return handleServiceResponse(serviceResponse, HttpStatus.CREATED);
    }

    @PostMapping("/join/organisation")
    public ResponseEntity<?> joinOrganisation(@Valid @RequestBody CommitteeMemberJoinOrganisationRequest committeeMemberJoinOrganisationRequest) {
        var serviceResponse = committeeMemberService.joinOrganisation(committeeMemberJoinOrganisationRequest);
        return handleServiceResponse(serviceResponse, HttpStatus.CREATED);
    }
}
