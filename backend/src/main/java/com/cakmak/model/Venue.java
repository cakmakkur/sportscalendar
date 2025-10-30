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

    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    private List<Country> countries;

    public Venue() {}

    public Venue(List<Event> events, List<Country> countries) {
        this.events = events;
        this.countries = countries;
    }

    public String getId() {
        return id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
