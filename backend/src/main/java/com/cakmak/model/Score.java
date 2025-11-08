package com.cakmak.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "event")
    private Event event;

    private String score;

    @Column(name = "scored_at")
    private LocalDateTime scoredAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "player")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "team")
    private Team team;

    public Score() {}

    public Score(Event event,
                 String score,
                 LocalDateTime scoredAt,
                 Player player,
                 Team team) {
        this.event = event;
        this.score = score;
        this.scoredAt = scoredAt;
        this.player = player;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public LocalDateTime getScoredAt() {
        return scoredAt;
    }

    public void setScoredAt(LocalDateTime scoredAt) {
        this.scoredAt = scoredAt;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
