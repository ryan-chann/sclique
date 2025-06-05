package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.CreateImageRequest;
import com.example.sunway.sclique.models.GetImageByEntityIdResponse;
import com.example.sunway.sclique.models.CreateImageResponse;
import com.example.sunway.sclique.models.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    ServiceResponse<CreateImageResponse> saveEventAdvertisementImage(MultipartFile eventAdvertisementImage, CreateImageRequest request) throws IOException;

    ServiceResponse<List<GetImageByEntityIdResponse>> getImageByEntityId(String entityIdString);
}
