package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.SaveImageRequest;
import com.example.sunway.sclique.services.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final IImageService imageService;

    @Autowired
    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload/event/advertisement")
    public ResponseEntity<String> uploadEventAdvertisementImage(
            @RequestParam("files") List<MultipartFile> files,
            @RequestBody SaveImageRequest request
    ) {
        try {
            imageService.saveEventAdvertisementImage(files, request);
            return ResponseEntity.ok("Uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }
}
