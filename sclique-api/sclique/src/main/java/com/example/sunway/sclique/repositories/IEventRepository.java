package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<Event, UUID>
{
    @Query(
        "SELECT event.title FROM Event event " +
        "WHERE LOWER(event.title) LIKE LOWER(CONCAT('%', ?1 , '%')) OR str(event.id) = ?1"
    )
    Page<String> findEventTitleByIdOrTitleContainingIgnoreCase(String query, Pageable pageable);
}