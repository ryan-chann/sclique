package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.Image;
import com.example.sunway.sclique.mapper.IImageMapper;
import com.example.sunway.sclique.models.GetImageByEntityIdResponse;
import com.example.sunway.sclique.models.SaveImageRequest;
import com.example.sunway.sclique.models.SaveImageResponse;
import com.example.sunway.sclique.models.ServiceResponse;
import com.example.sunway.sclique.repositories.IImageRepository;
import com.example.sunway.sclique.services.IImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
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

    public ServiceResponse<SaveImageResponse> saveEventAdvertisementImage(MultipartFile eventAdvertisementImage, SaveImageRequest request) throws IOException {
        var response = new ServiceResponse<SaveImageResponse>();

        BufferedImage bufferedImage = ImageIO.read(eventAdvertisementImage.getInputStream());

        if (bufferedImage == null) {
            response.setMessage("File: " + eventAdvertisementImage.getOriginalFilename() + " is either empty or not an image");
            return response;
        }

        Image image = imageMapper.saveImageRequestToImage(request);
        image.setFileName(eventAdvertisementImage.getOriginalFilename());
        image.setMimeType(eventAdvertisementImage.getContentType());
        image.setImageData(eventAdvertisementImage.getBytes());
        image.setFileSize(eventAdvertisementImage.getSize());

        imageRepository.save(image);

        response.setSuccess(true);
        response.setMessage("Image saved successfully");
        return response;
    }

    @Transactional
    public ServiceResponse<List<GetImageByEntityIdResponse>> getImageByEntityId(String entityIdString) {
        var serviceResponse = new ServiceResponse<List<GetImageByEntityIdResponse>>();
        UUID entityId;

        try {
            entityId = UUID.fromString(entityIdString);
        } catch (IllegalArgumentException ex) {
            serviceResponse.setSuccess(false);
            serviceResponse.setMessage("Invalid UUID format: " + ex.getMessage());
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
