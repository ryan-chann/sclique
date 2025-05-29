package com.example.sunway.sclique.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImageType {
    COMMITTEE_MEMBER_FACE_IMAGE(0),
    ORGANISATION_PROFILE_IMAGE(1),
    ORGANISATION_COVER_IMAGE(2),
    EVENT_ADVERTISEMENT_IMAGE(3);

    private final int id;

    public static ImageType fromId(int id) {
        for (ImageType imageType : ImageType.values()) {
            if (imageType.id == id) {
                return imageType;
            }
        }
        throw new IllegalArgumentException("Unknown image type: " + id);
    }
}
