package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.repositories.IOrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationService {
    private final IOrganisationRepository organisationRepository;

    @Autowired
    public OrganisationService(IOrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    public boolean createOrganisation(){

    }
}
