package com.example.sunway.sclique.entitities;

import com.example.sunway.sclique.converter.EntityTypeConverter;
import com.example.sunway.sclique.converter.ImageTypeConverter;
import com.example.sunway.sclique.entitities.base.EntityBase;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_id")
    private UUID entityId;

    @Column(name = "entity_type")
    @Convert(converter = EntityTypeConverter.class)
    private EntityType entityType;

    @Column(name = "image_type")
    @Convert(converter = ImageTypeConverter.class)
    private ImageType imageType;

    @Column(length = 512, name = "file_name")
    private String fileName;

    @Column(length = 128, name = "mime_type")
    private String mimeType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image_data")
    private byte[] imageData;
}
