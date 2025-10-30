package com.cakmak.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    private String id = UUID.randomUUID().toString();
}
