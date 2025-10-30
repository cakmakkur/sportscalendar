package com.cakmak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_teams")
public class EventTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

    public EventTeam() {}

    public EventTeam(Event event, Team team) {
        this.event = event;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
