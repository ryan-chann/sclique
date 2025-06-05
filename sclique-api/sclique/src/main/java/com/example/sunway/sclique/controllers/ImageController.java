package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.CreateImageRequest;
import com.example.sunway.sclique.services.IImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final IImageService imageService;

    @Autowired
    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload/event/advertisement", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadEventAdvertisementImage(
            @RequestPart("files") @Valid MultipartFile file,
            @RequestPart("request") CreateImageRequest request
    ) {
        try {
            var serviceResponse = imageService.saveEventAdvertisementImage(file, request);

            if (!serviceResponse.isSuccess()) {
                return new ResponseEntity<>(serviceResponse.getErrorMessage(), HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok().body(serviceResponse.getData());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/images/{entityId}")
    public ResponseEntity<?> getImageByEntityId(@PathVariable String entityId) {
        var serviceResponse = imageService.getImageByEntityId(entityId);

        if (!serviceResponse.isSuccess()) {
            return ResponseEntity.badRequest().body(serviceResponse.getErrorMessage());
        }

        return ResponseEntity.ok(serviceResponse.getData());
    }
}
