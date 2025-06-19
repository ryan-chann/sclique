package com.example.sunway.sclique.models.image;

import com.example.sunway.sclique.enums.ImageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetImageByEntityIdResponse {
    private ImageType imageType;
    private String fileName;
    private String mimeType;
    private long fileSize;
    private String imageDataBase64;
}
