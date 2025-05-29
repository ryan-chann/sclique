package com.example.sunway.sclique.mapper;

import com.example.sunway.sclique.entities.Image;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;
import com.example.sunway.sclique.models.SaveImageRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface IImageMapper {

    @Mapping(source = "entityId", target = "entityId", qualifiedByName = "stringToUUID")
    @Mapping(source = "entityType", target = "entityType", qualifiedByName = "intToEntityType")
    @Mapping(source = "imageType", target = "imageType", qualifiedByName = "intToImageType")
    Image saveImageRequestToImage(SaveImageRequest saveImageRequest);

    @Named("stringToUUID")
    default UUID stringToUUID(String id) {
        return id == null ? null : UUID.fromString(id);
    }

    @Named("intToEntityType")
    default EntityType intToEntityType(int type) {
        for (EntityType entityType : EntityType.values()) {
            if (entityType.getId() == type) {
                return entityType;
            }
        }

        return null;
    }

    @Named("intToImageType")
    default ImageType intToImageType(int type) {
        for (ImageType imageType : ImageType.values()) {
            if (imageType.getId() == type) {
                return imageType;
            }
        }

        return null;
    }

}
