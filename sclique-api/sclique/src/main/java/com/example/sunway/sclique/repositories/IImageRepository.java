package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IImageRepository extends JpaRepository<Image, UUID> {

    @Query(
        "SELECT image FROM Image image " +
        "WHERE image.entityId = ?1"
    )
    List<Image> findByEntityId(UUID entityId);


}
