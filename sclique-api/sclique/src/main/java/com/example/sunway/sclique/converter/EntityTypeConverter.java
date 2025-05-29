package com.example.sunway.sclique.converter;

import com.example.sunway.sclique.enums.EntityType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EntityTypeConverter implements AttributeConverter<EntityType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EntityType entityType) {
        if (entityType == null) {
            return null;
        }
        return entityType.getId();
    }

    @Override
    public EntityType convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        }

        return EntityType.fromId(id);
    }
}

