package com.example.sunway.sclique.services.implementation;

import com.example.sunway.sclique.entities.Image;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import com.example.sunway.sclique.mapper.IImageMapper;
import com.example.sunway.sclique.models.SaveImageRequest;
import com.example.sunway.sclique.repositories.IImageRepository;
import com.example.sunway.sclique.services.IImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements IImageService {
    private final IImageRepository imageRepository;
    private final IImageMapper imageMapper;

    @Autowired
    public ImageServiceImpl(IImageRepository imageRepository, IImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public boolean saveEventAdvertisementImage(List<MultipartFile> eventAdvertisementImages, SaveImageRequest request) throws IOException {
        List<Image> savedImages = new ArrayList<>();

        for (MultipartFile file : eventAdvertisementImages) {
            Image image = new Image();

            imageMapper.saveImageRequestToImage(request);
            image.setFileName(file.getOriginalFilename());
            image.setMimeType(file.getContentType());
            image.setImageData(file.getBytes());

            savedImages.add(imageRepository.save(image));
        }

        return true;
    }
}
