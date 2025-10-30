package com.cakmak.dtos;

public record EventPlayerDto(
        String id,
        EventDto event,
        PlayerDto player
) {
}
