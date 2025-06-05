package com.example.sunway.sclique.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateImageResponse {
    private String mimeType;
    private String fileName;
    private Long fileSize;
}
