package com.example.sunway.sclique.services;

import com.example.sunway.sclique.entities.Image;
import com.example.sunway.sclique.models.GetImageByEntityIdResponse;
import com.example.sunway.sclique.models.SaveImageRequest;
import com.example.sunway.sclique.models.SaveImageResponse;
import com.example.sunway.sclique.models.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    ServiceResponse<SaveImageResponse> saveEventAdvertisementImage(MultipartFile eventAdvertisementImage, SaveImageRequest request) throws IOException;

    ServiceResponse<List<GetImageByEntityIdResponse>> getImageByEntityId(String entityIdString);
}
