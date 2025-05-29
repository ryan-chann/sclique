package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.CommitteeMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICommitteeMemberRepository extends JpaRepository<CommitteeMember, UUID> {

}
