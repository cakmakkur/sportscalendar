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
    @JoinColumn(name = "competition_type")
    private CompetitionType competitionType;

    @ManyToOne
    @JoinColumn(name = "event_category")
    private EventCategory eventCategory;

    public EventType() {}

    public EventType(Long id,
                     String name,
                     CompetitionType competitionType,
                     EventCategory eventCategory) {
        this.id = id;
        this.name = name;
        this.competitionType = competitionType;
        this.eventCategory = eventCategory;
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

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
}
