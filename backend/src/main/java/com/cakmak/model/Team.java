package com.cakmak.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    private String id = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<TeamPlayer> teamPlayers;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<EventTeam> eventTeams;

    @OneToMany(mappedBy = "team")
    private List<Score> scores;

    public Team() {}

    public Team(List<TeamPlayer> teamPlayers,
                List<EventTeam> eventTeams,
                List<Score> scores) {
        this.teamPlayers = teamPlayers;
        this.eventTeams = eventTeams;
        this.scores = scores;
    }

    public String getId() {
        return id;
    }

    public List<TeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(List<TeamPlayer> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public List<EventTeam> getEventTeams() {
        return eventTeams;
    }

    public void setEventTeams(List<EventTeam> eventTeams) {
        this.eventTeams = eventTeams;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
