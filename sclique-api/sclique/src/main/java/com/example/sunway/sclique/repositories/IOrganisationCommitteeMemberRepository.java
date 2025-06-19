package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.OrganisationCommitteeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrganisationCommitteeMemberRepository extends JpaRepository<OrganisationCommitteeMember, Long> {

}
