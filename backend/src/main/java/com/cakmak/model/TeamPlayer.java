package com.cakmak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams_player")
public class TeamPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player")
    private Player player ;

    public TeamPlayer() {}

    public TeamPlayer(Team team,
                      Player player) {
        this.team = team;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
