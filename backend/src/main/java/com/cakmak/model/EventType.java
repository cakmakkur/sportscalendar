package com.cakmak.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "event_types")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "eventType", fetch = FetchType.LAZY)
    private List<Event> events;

    @ManyToOne
    private CompetitionType competitionType;

    public EventType() {}

    public EventType(Long id,
                     String name,
                     CompetitionType competitionType) {
        this.id = id;
        this.name = name;
        this.competitionType = competitionType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }
}
