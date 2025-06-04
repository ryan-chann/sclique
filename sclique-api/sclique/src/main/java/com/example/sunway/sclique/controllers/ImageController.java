package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.SaveImageRequest;
import com.example.sunway.sclique.services.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final IImageService imageService;

    @Autowired
    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload/event/advertisement", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadEventAdvertisementImage(
            @RequestPart("files") MultipartFile file,
            @RequestPart("request") SaveImageRequest request
    ) {

        try {
            var serviceResponse = imageService.saveEventAdvertisementImage(file, request);

            if (!serviceResponse.isSuccess()) {
                return new ResponseEntity<>(serviceResponse.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok().body(serviceResponse.getMessage());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/images/{entityId}")
    public ResponseEntity<?> getImageByEntityId(@PathVariable String entityId) {
        var serviceResponse = imageService.getImageByEntityId(entityId);

        if (!serviceResponse.isSuccess()) {
            return ResponseEntity.badRequest().body(serviceResponse.getMessage());
        }

        return ResponseEntity.ok(serviceResponse.getData());
    }
}
