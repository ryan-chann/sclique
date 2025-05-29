package com.example.sunway.sclique.repository;

import com.example.sunway.sclique.entitity.CommitteeMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICommitteeMemberRepository extends JpaRepository<CommitteeMember, UUID> {

}
