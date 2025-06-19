package com.example.sunway.sclique.mapper;

import com.example.sunway.sclique.entities.CommitteeMember;
import com.example.sunway.sclique.models.committeeMember.CreateCommitteeMemberRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICommitteeMemberMapper {
    CommitteeMember createCommitteeMemberRequestToCommitteeMember(CreateCommitteeMemberRequest createCommitteeMemberRequest);
}
