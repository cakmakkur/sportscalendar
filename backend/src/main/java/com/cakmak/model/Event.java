package com.cakmak.model;

import com.cakmak.enums.EventStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {
    @Id
    private String id = UUID.randomUUID().toString();

    private OffsetDateTime date;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    private EventStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_type")
    private EventType eventType;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Venue venue;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Score> scores;

    @OneToOne(mappedBy = "event")
    private Livestream livestream;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<EventPlayer> eventPlayers;
}
