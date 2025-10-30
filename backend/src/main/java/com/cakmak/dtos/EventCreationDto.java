package com.cakmak.dtos;

import com.cakmak.enums.EventStatus;
import com.cakmak.model.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.List;

public record EventCreationDto (
        @NotNull OffsetDateTime date,
        @NotNull EventStatus status,
        @NotNull EventType eventType,
        String description,
        List<EventPlayer> eventPlayers,
        List<EventTeam> eventTeams,
        Livestream livestream,
        List<Score> scores,
        @NotNull Venue venue
) {}