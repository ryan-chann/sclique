package com.example.sunway.sclique.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EntityType {
    COMMITTEE_MEMBER(0),
    EVENT(1),
    ORGANISATION(2);

    private final int id;

    public static EntityType fromId(int id) {
        for (EntityType entityType : EntityType.values()) {
            if (entityType.id == id) {
                return entityType;
            }
        }
        throw new IllegalArgumentException("Unknown entity type: " + id);
    }
}
