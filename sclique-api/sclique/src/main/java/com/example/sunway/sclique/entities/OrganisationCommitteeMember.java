package com.example.sunway.sclique.entities;

import com.example.sunway.sclique.entities.base.EntityBase;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organisation_committee_member")
public class OrganisationCommitteeMember extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @ManyToOne
    @JoinColumn(name = "committee_member_id")
    private CommitteeMember committeeMember;

    @Size(max = 128)
    @Column(length = 128)
    private String position;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @Nullable
    private OrganisationCommitteeMember manager;
}
