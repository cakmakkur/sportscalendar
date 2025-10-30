package com.cakmak.model;

import com.sun.tools.javac.util.List;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "players")
public class Player {
    @Id
    private String id = UUID.randomUUID().toString();

    private String firstname;

    private String lastname;

    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

    @OneToMany(mappedBy = "player")
    private List<EventPlayer> eventPlayers;
}
