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

    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

    public Venue() {}

    public Venue(List<Event> events, Country countries) {
        this.events = events;
        this.country = countries;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
