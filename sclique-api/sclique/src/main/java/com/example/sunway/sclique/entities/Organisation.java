package com.example.sunway.sclique.entities;

import com.example.sunway.sclique.entities.base.EntityBase;

import com.example.sunway.sclique.enums.EntityType;
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

    @Size(max = 2048)
    @Column(length = 2048)
    private String description;

    @OneToMany(
            mappedBy = "organiser",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Event> events;

    @OneToMany(
            mappedBy = "organisation",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<OrganisationCommitteeMember> committeeMembers;
}
