package com.cakmak.dtos;

public record EventTeamDto(
        String id,
        TeamDto team,
        EventDto event
) {
}
