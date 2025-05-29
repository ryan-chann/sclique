package com.example.sunway.sclique.repositories;

import com.example.sunway.sclique.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IImageRepository extends JpaRepository<Image, UUID> {

}
