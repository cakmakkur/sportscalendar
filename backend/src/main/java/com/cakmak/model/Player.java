package com.cakmak.model;

import com.sun.tools.javac.util.List;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "players")
public class Player {
    @Id
    private String id = UUID.randomUUID().toString();

    private String firstname;

    private String lastname;

    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

    @OneToMany(mappedBy = "player")
    private List<EventPlayer> eventPlayers;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<TeamPlayer> teamPlayers;

    public Player() {}

    public Player(
            String firstname,
            String lastname,
            Country country,
            List<EventPlayer> eventPlayers,
            List<TeamPlayer> teamPlayers) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.eventPlayers = eventPlayers;
        this.teamPlayers = teamPlayers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<EventPlayer> getEventPlayers() {
        return eventPlayers;
    }

    public void setEventPlayers(List<EventPlayer> eventPlayers) {
        this.eventPlayers = eventPlayers;
    }

    public List<TeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(List<TeamPlayer> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }
}
