package com.cakmak.dtos;

import com.cakmak.enums.EventStatus;
import com.cakmak.model.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.OffsetDateTime;
import java.util.List;

public record EventDto (
        String id,
        @NotNull OffsetDateTime date,
        OffsetDateTime createdAt,
        EventCategoryDto category,
        @NotNull EventStatus status,
        @NotNull EventTypeDto eventType,
        @Length(min = 1, max =255)
        String description,
        List<String> playerIds,
        List<String> teamIds,
        LivestreamDto livestream,
        List<ScoreDto> scores,
        @NotNull VenueDto venue
) {}
