package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.Event;

import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<Event, UUID>
{
    @Query("""
        SELECT event.title FROM Event event
        WHERE LOWER(event.title) LIKE LOWER(CONCAT('%', ?1 , '%')) OR str(event.id) = ?1
        ORDER BY event.createdAt DESC 
    """)
    Page<String> findEventTitleByIdOrTitleContainingIgnoreCase(String query, Pageable pageable);

    @Query("""
        SELECT 
            event.id,
            event.title,
            event.venue, 
            image.imageData, 
            image.mimeType
        FROM Event event
        LEFT JOIN Image image
            ON image.entityId = event.id
            AND image.entityType = ?2
            AND image.imageType = ?3
        WHERE LOWER(event.title) LIKE LOWER(CONCAT('%', ?1, '%'))
        ORDER BY event.createdAt DESC
    """)
    Page<Object[]> findEventSummaryByTitleContainingIgnoreCase(String query, EntityType entityType, ImageType imageType, Pageable pageable);
}