package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<Event, UUID>
{
    @Query(
            "SELECT e FROM Event e " +
            "WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR str(e.id) = :keyword"
    )
    List<Event> searchByIdOrTitle(@Param("keyword") String keyword);
}