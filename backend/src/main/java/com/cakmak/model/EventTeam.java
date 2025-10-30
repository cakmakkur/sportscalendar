package com.cakmak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_teams")
public class EventTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
