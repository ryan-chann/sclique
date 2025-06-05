package com.example.sunway.sclique.controllers;

import com.example.sunway.sclique.models.CreateOrganisationRequest;
import com.example.sunway.sclique.models.SearchOrganisationsRequest;
import com.example.sunway.sclique.services.IOrganisationService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<?> createOrganisation(@RequestBody @Valid CreateOrganisationRequest request) {
        var serviceResponse = organisationService.createOrganisation(request);

        return handleServiceResponse(serviceResponse, HttpStatus.OK);
    }
}
