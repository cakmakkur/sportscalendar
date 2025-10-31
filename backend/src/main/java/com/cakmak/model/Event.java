package com.cakmak.model;

import com.cakmak.enums.EventStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_type")
    private EventType eventType;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venue")
    private Venue venue;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Score> scores;

    @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Livestream livestream;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<EventPlayer> eventPlayers;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<EventTeam> eventTeams;

    public Event() {}

    public Event (OffsetDateTime date,
                  EventStatus status,
                  EventType eventType,
                  String description,
                  Venue venue,
                  List<Score> scores,
                  Livestream livestream,
                  List<EventPlayer> eventPlayers,
                  List<EventTeam> eventTeams) {
        this.date = date;
        this.status = status;
        this.eventType = eventType;
        this.description = description;
        this.venue = venue;
        this.scores = scores;
        this.livestream = livestream;
        this.eventPlayers = eventPlayers;
        this.eventTeams = eventTeams;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Livestream getLivestream() {
        return livestream;
    }

    public void setLivestream(Livestream livestream) {
        this.livestream = livestream;
    }

    public List<EventPlayer> getEventPlayers() {
        return eventPlayers;
    }

    public void setEventPlayers(List<EventPlayer> eventPlayers) {
        this.eventPlayers = eventPlayers;
    }

    public void addEventPlayer(EventPlayer eventPlayer) {
        this.eventPlayers.add(eventPlayer);
    }

    public List<EventTeam> getEventTeams() {
        return eventTeams;
    }
    public void setEventTeams(List<EventTeam> eventTeams) {
        this.eventTeams = eventTeams;
    }

}
