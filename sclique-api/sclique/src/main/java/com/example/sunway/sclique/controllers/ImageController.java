package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.CreateImageRequest;
import com.example.sunway.sclique.services.IImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.sunway.sclique.utils.ResponseUtil.handleServiceResponse;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final IImageService imageService;

    @Autowired
    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(
            @RequestPart("files") @Valid MultipartFile file,
            @RequestPart("request") CreateImageRequest request
    ) {
        try {
            var serviceResponse = imageService.saveImage(file, request);

            return handleServiceResponse(serviceResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/{entityId}")
    public ResponseEntity<?> getImageByEntityId(@PathVariable String entityId) {
        var serviceResponse = imageService.getImageByEntityId(entityId);

        return handleServiceResponse(serviceResponse, HttpStatus.OK);
    }
}
