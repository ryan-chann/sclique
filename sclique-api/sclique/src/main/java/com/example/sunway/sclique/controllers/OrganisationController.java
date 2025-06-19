package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.organisation.CreateOrganisationRequest;
import com.example.sunway.sclique.models.organisation.GetOrganisationProfileRequest;
import com.example.sunway.sclique.models.organisation.SearchOrganisationsRequest;
import com.example.sunway.sclique.services.IOrganisationService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.sunway.sclique.utils.ResponseUtil.handleServiceResponse;

@RestController
@RequestMapping("api/v1/organisation")
public class OrganisationController {
    private final IOrganisationService organisationService;

    @Autowired
    public OrganisationController(IOrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @GetMapping("/search/names")
    public ResponseEntity<?> searchOrganisation(@ModelAttribute @Valid SearchOrganisationsRequest searchOrganisationsRequest) {
        var serviceResponse = organisationService.getOrganisationNameByMatchingIdOrTitle(searchOrganisationsRequest);

        return handleServiceResponse(serviceResponse, HttpStatus.OK);
    }

    @GetMapping("/search/listing")
    public ResponseEntity<?> searchEventListing(@ModelAttribute @Valid SearchOrganisationsRequest searchOrganisationsRequest) {
        var serviceResponse = organisationService.getOrganisationNameAndImageByMatchingTitle(searchOrganisationsRequest);

        return handleServiceResponse(serviceResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createOrganisation(
            @RequestPart("request") @Valid CreateOrganisationRequest request,
            @RequestPart("organisationProfileImage") @Valid MultipartFile organisationProfileImage,
            @RequestPart("organisationCoverImage") MultipartFile organisationCoverImage
    ) {
        var serviceResponse = organisationService.createOrganisation(request, organisationProfileImage, organisationCoverImage);
        return handleServiceResponse(serviceResponse, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getOrganisationProfile(@ModelAttribute @Valid GetOrganisationProfileRequest getOrganisationProfileRequest) {
        var serviceResponse = organisationService.getOrganisationProfile(getOrganisationProfileRequest);
        return handleServiceResponse(serviceResponse, HttpStatus.OK);
    }
}
