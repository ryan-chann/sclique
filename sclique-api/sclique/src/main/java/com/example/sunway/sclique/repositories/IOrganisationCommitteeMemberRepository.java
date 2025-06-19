package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.OrganisationCommitteeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IOrganisationCommitteeMemberRepository extends JpaRepository<OrganisationCommitteeMember, Long> {
    List<OrganisationCommitteeMember> findByOrganisationId(UUID organisationId);
}
