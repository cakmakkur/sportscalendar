package com.cakmak.dtos;

import org.hibernate.validator.constraints.Length;

import java.time.OffsetDateTime;

public record ScoreDto(
        String id,
        String eventId,
        String playerId,
        String teamId,
        @Length(min = 1, max =255)
        String score,
        OffsetDateTime scoredAt
) {
}
