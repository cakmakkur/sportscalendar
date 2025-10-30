package com.cakmak.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "event")
    private Event event;

    private String score;

    @Column(name = "scored_at")
    private OffsetDateTime scoredAt;
}
