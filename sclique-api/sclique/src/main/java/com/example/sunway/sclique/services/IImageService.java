package com.example.sunway.sclique.services;

import com.example.sunway.sclique.models.image.CreateImageRequest;
import com.example.sunway.sclique.models.image.GetImageByEntityIdResponse;
import com.example.sunway.sclique.models.image.CreateImageResponse;
import com.example.sunway.sclique.models.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    ServiceResponse<CreateImageResponse> saveImage(MultipartFile eventAdvertisementImage, CreateImageRequest request) throws IOException;

    ServiceResponse<List<GetImageByEntityIdResponse>> getImageByEntityId(String entityIdString);
}
