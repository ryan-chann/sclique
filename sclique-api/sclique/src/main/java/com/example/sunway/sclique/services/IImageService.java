package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.SaveImageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    boolean saveEventAdvertisementImage(List<MultipartFile> eventAdvertisementImages, SaveImageRequest request) throws IOException;
}
