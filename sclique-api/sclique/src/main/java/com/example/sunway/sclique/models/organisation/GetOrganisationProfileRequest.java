package com.example.sunway.sclique.models.organisation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrganisationProfileRequest {
    private String organisationId;
}
