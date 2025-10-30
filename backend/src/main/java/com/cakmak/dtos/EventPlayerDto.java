package com.cakmak.dtos;

public record EventPlayerDto(
        Long id,
        String eventId,
        PlayerDto player
) {
}
