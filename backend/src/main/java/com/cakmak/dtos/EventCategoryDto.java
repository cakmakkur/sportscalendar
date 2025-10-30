package com.cakmak.dtos;

import org.hibernate.validator.constraints.Length;

public record EventCategoryDto(
        Long id,
        @Length(min = 1, max =64)
        String name,
        @Length(min = 1, max =255)
        String description
) {
}
