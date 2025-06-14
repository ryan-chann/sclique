package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.EventFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IEventFeeRepository extends JpaRepository<EventFee, Long> {
    List<EventFee> findByEventId(UUID eventId);
}
