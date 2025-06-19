package com.example.sunway.sclique.entities;

import com.example.sunway.sclique.entities.base.EntityBase;

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
@Table(name = "committee_member")
public class CommitteeMember extends EntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Size(max = 128)
    @Column(length = 128)
    private String name;

    @Size(max = 128)
    @Column(length = 128)
    private String email;

    @Size(max = 32)
    @Column(length = 32, name = "phone_number")
    private String phoneNumber;

    @OneToMany(
            mappedBy = "committeeMember",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<OrganisationCommitteeMember> organisations;
}
