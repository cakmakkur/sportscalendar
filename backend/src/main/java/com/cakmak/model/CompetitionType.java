package com.cakmak.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "competition_types")
public class CompetitionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany(mappedBy = "competitionType", fetch = FetchType.LAZY)
    private List<EventType> eventTypes;

    public CompetitionType() {}

    public CompetitionType(String type,
                           List<EventType> eventTypes) {
        this.type = type;
        this.eventTypes = eventTypes;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<EventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(List<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }
}
