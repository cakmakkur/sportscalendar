package com.cakmak.util;

import com.cakmak.dtos.EventDto;
import com.cakmak.enums.EventStatus;
import com.cakmak.model.*;


public class Mapper {

    public static EventDto toEventDto (Event e) {
        return new EventDto(
                e.getId(),
                e.getDate(),
                e.getCreatedAt(),
                e.getEventType().getEventCategory(),
                EventStatus.fromString(e.getStatus().toString()),
                e.getEventType(),
                e.getDescription(),
                e.getEventPlayers(),
                e.getEventTeams(),
                e.getLivestream(),
                e.getScores(),
                e.getVenue()
        );
    }

}
