package com.cakmak.dtos;

import org.hibernate.validator.constraints.Length;

public record PlayerDto(
        String id,
        @Length(min = 1, max =255)
        String firstname,
        @Length(min = 1, max =255)
        String lastname,
        String country
) {
}
