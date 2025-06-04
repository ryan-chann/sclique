package com.example.sunway.sclique.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetImageByEntityIdResponse {
    private String fileName;
    private String mimeType;
    private long fileSize;
    private String imageDataBase64;
}
