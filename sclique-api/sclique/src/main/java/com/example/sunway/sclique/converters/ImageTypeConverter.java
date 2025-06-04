package com.example.sunway.sclique.converters;

import com.example.sunway.sclique.enums.ImageType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class ImageTypeConverter implements AttributeConverter<ImageType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ImageType imageType) {
        if(imageType == null){
            return null;
        }

        return imageType.getId();
    }

    @Override
    public ImageType convertToEntityAttribute(Integer id) {
        if(id == null){
            return null;
        }

        return ImageType.fromId(id);
    }
}
