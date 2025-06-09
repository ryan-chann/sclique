package com.example.sunway.sclique.entities;

import com.example.sunway.sclique.entities.base.EntityBase;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event extends EntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Size(max = 128)
    @Column(length = 128)
    private String title;

    @Size(max = 2048)
    @Column(length = 2048)
    private String description;

    @Size(max = 128)
    @Column(length = 128)
    private String venue;

    @Column(name = "duration_in_minutes")
    private int durationInMinutes;

    @Size(max = 256)
    @Column(length = 256, name = "participation_link")
    private String participationLink;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "event",
            orphanRemoval = true
    )
    private List<EventFee> eventFees;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "event",
            orphanRemoval = true
    )
    private List<EventSession> eventSessions;
}
