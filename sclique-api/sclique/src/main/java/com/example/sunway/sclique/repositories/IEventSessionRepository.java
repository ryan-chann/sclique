package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.EventSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IEventSessionRepository extends JpaRepository<EventSession, Long> {

    List<EventSession> findByEventId(UUID eventId);
}
