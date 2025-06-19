package com.example.sunway.sclique.models.organisation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrganisationNameAndImageResponse {
    private String id;
    private String name;
    private String imageDataBase64;
    private String mimeType;
}
