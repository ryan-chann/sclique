package com.example.sunway.sclique.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateImageRequest {
    private String entityId;
    private int entityType;
    private int imageType;
}
