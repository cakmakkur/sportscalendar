package com.cakmak.dtos;

import org.hibernate.validator.constraints.Length;

public record VenueDto(
        String id,
        @Length(min = 1, max =255)
        String name,
        String country
) {
}
