package com.cakmak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_player")
public class EventPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
