package com.cakmak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "competition_types")
public class CompetitionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
