package com.example.sunway.sclique.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveImageResponse {
    private String mimeType;
    private String fileName;
    private Long fileSize;
}
