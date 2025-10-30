package com.cakmak.dtos;

import com.cakmak.enums.EventStatus;
import com.cakmak.model.*;

import java.time.OffsetDateTime;
import java.util.List;

public record EventDto (
        String id,
        OffsetDateTime date,
        OffsetDateTime createdAt,
        EventStatus status,
        EventType eventType,
        String description,
        List<EventPlayer> eventPlayers,
        List<EventTeam> eventTeams,
        Livestream livestream,
        List<Score> scores,
        Venue venue
) {}
