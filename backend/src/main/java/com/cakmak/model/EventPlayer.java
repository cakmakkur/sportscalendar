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

    public EventPlayer() {}

    public EventPlayer(Event event,
                       Player player) {
        this.event = event;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
