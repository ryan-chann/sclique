package com.example.sunway.sclique.entities;

import com.example.sunway.sclique.converters.EntityTypeConverter;
import com.example.sunway.sclique.converters.ImageTypeConverter;
import com.example.sunway.sclique.entities.base.EntityBase;
import com.example.sunway.sclique.enums.EntityType;
import com.example.sunway.sclique.enums.ImageType;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image extends EntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "entity_id")
    private UUID entityId;

    private int sequence;

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

    @Column(name = "file_size")
    private Long fileSize;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image_data")
    private byte[] imageData;
}
