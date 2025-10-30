package com.cakmak.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "venues")
public class Venue {
    @Id
    private String id = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    private List<Event> events;
}
