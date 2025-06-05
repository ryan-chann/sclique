package com.example.sunway.sclique.models;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateImageRequest {
    @NotBlank
    private String entityId;
    private int entityType;
    private int imageType;
}
