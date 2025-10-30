package com.cakmak.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "players")
public class Player {
    @Id
    private String id = UUID.randomUUID().toString();
}
