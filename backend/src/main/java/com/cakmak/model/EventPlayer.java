package com.cakmak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_player")
public class EventPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "player")
    private Player player;
}
