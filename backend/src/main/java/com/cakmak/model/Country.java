package com.cakmak.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Country() {}

    public Country(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Team> teams;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Team> players;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Team> venues;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getPlayers() {
        return players;
    }

    public void setPlayers(List<Team> players) {
        this.players = players;
    }

    public List<Team> getVenues() {
        return venues;
    }

    public void setVenues(List<Team> venues) {
        this.venues = venues;
    }
}
