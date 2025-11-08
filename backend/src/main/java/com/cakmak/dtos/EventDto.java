package com.cakmak.dtos;

import com.cakmak.enums.EventStatus;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

public record EventDto (
        String id,
        @NotNull Date date,
        Date createdAt,
        EventCategoryDto category,
        @NotNull EventStatus status,
        @NotNull EventTypeDto eventType,
        @Length(min = 1, max =255)
        String description,
        List<PlayerDto> players,
        List<TeamDto> teams,
        LivestreamDto livestream,
        List<ScoreDto> scores,
        @NotNull VenueDto venue
) {}
