package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.Image;
import com.example.sunway.sclique.mapper.IImageMapper;
import com.example.sunway.sclique.models.CreateImageRequest;
import com.example.sunway.sclique.models.GetImageByEntityIdResponse;
import com.example.sunway.sclique.models.CreateImageResponse;
import com.example.sunway.sclique.models.ServiceResponse;
import com.example.sunway.sclique.repositories.IImageRepository;
import com.example.sunway.sclique.services.IImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements IImageService {
    private final IImageRepository imageRepository;
    private final IImageMapper imageMapper;

    @Autowired
    public ImageServiceImpl(IImageRepository imageRepository, IImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public ServiceResponse<CreateImageResponse> saveImage(MultipartFile imageFile, CreateImageRequest request) throws IOException {
        var serviceResponse = new ServiceResponse<CreateImageResponse>();
        var saveImageResponse = new CreateImageResponse();

        BufferedImage bufferedImage = ImageIO.read(imageFile.getInputStream());

        if (bufferedImage == null) {
            serviceResponse.setErrorMessage("File: " + imageFile.getOriginalFilename() + " is either empty or not an image");
            return serviceResponse;
        }

        Image image = imageMapper.saveImageRequestToImage(request);
        image.setFileName(imageFile.getOriginalFilename());
        image.setMimeType(imageFile.getContentType());
        image.setImageData(imageFile.getBytes());
        image.setFileSize(imageFile.getSize());

        imageRepository.save(image);

        saveImageResponse.setFileSize(imageFile.getSize());
        saveImageResponse.setMimeType(imageFile.getContentType());
        saveImageResponse.setFileName(imageFile.getOriginalFilename());

        serviceResponse.setData(saveImageResponse);
        serviceResponse.setSuccess(true);

        return serviceResponse;
    }

    @Transactional
    public ServiceResponse<List<GetImageByEntityIdResponse>> getImageByEntityId(String entityIdString) {
        var serviceResponse = new ServiceResponse<List<GetImageByEntityIdResponse>>();
        UUID entityId;

        try {
            entityId = UUID.fromString(entityIdString);
        } catch (IllegalArgumentException ex) {
            serviceResponse.setSuccess(false);
            serviceResponse.setErrorMessage("Invalid UUID format: " + ex.getMessage());
            return serviceResponse;
        }

        List<Image> images = imageRepository.findByEntityId(entityId);

        List<GetImageByEntityIdResponse> responseList = images.stream()
                .map(image -> {
                    GetImageByEntityIdResponse response = new GetImageByEntityIdResponse();
                    response.setFileName(image.getFileName());
                    response.setMimeType(image.getMimeType());
                    response.setFileSize(image.getImageData() != null ? image.getImageData().length : 0);
                    response.setImageDataBase64(
                        image.getImageData() != null
                            ? Base64.getEncoder().encodeToString(image.getImageData())
                            : null
                    );
                    return response;
                })
                .collect(Collectors.toList());

        serviceResponse.setSuccess(true);
        serviceResponse.setData(responseList);
        return serviceResponse;
    }
}
