package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.Organisation;

import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import com.example.sunway.sclique.models.GetOrganisationNameAndImageResponse;
import com.example.sunway.sclique.models.SearchEventsRequest;
import com.example.sunway.sclique.models.ServiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrganisationRepository extends JpaRepository<Organisation, UUID> {

    @Query("""
        SELECT organisation.name FROM Organisation organisation 
        WHERE LOWER(organisation.name) LIKE LOWER(CONCAT('%', ?1 , '%')) OR str(organisation.id) = ?1
    """)
    Page<String> findOrganisationNameByIdOrTitleContainingIgnoreCase(String query, Pageable pageable);

    @Query("""
        SELECT organisation.name, image.imageData
        FROM Organisation organisation
        LEFT JOIN Image image 
            ON image.entityId = organisation.id 
                AND image.entityType = ?2 
                AND image.imageType = ?3        
        WHERE LOWER(organisation.name) LIKE LOWER(CONCAT('%', ?1, '%'))
    """)
    Page<Object[]> findOrganisationNameImageByTitleContainingIgnoreCase(String query, EntityType entityType, ImageType imageType, Pageable pageable);
}
