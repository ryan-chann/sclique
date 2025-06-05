package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.Organisation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrganisationRepository extends JpaRepository<Organisation, UUID> {

    @Query(
        "SELECT organisation.name FROM Organisation organisation " +
        "WHERE LOWER(organisation.name) LIKE LOWER(CONCAT('%', ?1 , '%')) OR str(organisation.id) = ?1"
    )
    Page<String> findOrganisationNameByIdOrTitleContainingIgnoreCase(String query, Pageable pageable);
}
