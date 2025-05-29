package com.example.sunway.sclique.entitities;

import com.example.sunway.sclique.entitities.base.EntityBase;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organisation")
public class Organisation extends EntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "profile_photo")
    private byte[] profilePhoto;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "cover_photo")
    private byte[] coverPhoto;

    @Size(max = 2048)
    @Column(length = 2048)
    private String description;

    @OneToMany(
            mappedBy = "organisation",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<OrganisationCommitteeMember> committeeMembers;
}
