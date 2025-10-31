package com.cakmak.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "venues")
public class Venue {
    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    private List<Event> events;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country")
    private Country country;

    public Venue() {}

    public Venue(Country countries, String name) {
        this.country = countries;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    public void addEvent(Event event) {
        events.add(event);
    }
}
