package com.cakmak.dtos;

import org.hibernate.validator.constraints.Length;

public record EventTypeDto(
        Long id,
        @Length(min = 1, max =64)
        String name,
        EventCategoryDto eventCategory,
        String competitionType
) {
}
