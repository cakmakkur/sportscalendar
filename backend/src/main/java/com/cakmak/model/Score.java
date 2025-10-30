package com.cakmak.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
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
    private OffsetDateTime scoredAt;

    public Score() {}

    public Score(Event event,
                 String score,
                 OffsetDateTime scoredAt) {
        this.event = event;
        this.score = score;
        this.scoredAt = scoredAt;
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

    public OffsetDateTime getScoredAt() {
        return scoredAt;
    }
}
