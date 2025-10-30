package com.cakmak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams_player")
public class TeamPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long productId;
}
