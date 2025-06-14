package com.example.sunway.sclique.models;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateImageRequest {
    @NotBlank
    private String entityId;
    private int entityType;
    private int imageType;
}
