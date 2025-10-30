package com.cakmak.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "event_types")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "eventType", fetch = FetchType.LAZY)
    private List<Event> events;
}
