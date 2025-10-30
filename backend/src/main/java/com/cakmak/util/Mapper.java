package com.cakmak.util;

import com.cakmak.dtos.EventDto;
import com.cakmak.model.*;


public class Mapper {

    public static EventDto toEventDto (Event e) {
        return new EventDto(
                e.getId(),
                e.getDate(),
                e.getCreatedAt(),
                e.getEventType().getEventCategory(),
                e.getStatus(),
                e.getEventType(),
                e.getDescription(),
                e.getEventPlayers(),
                e.getEventTeams(),
                e.getLivestream(),
                e.getScores(),
                e.getVenue()
        );
    }

    public static Event toEvent (EventDto dto) {
        Event event = new Event();

        event.setDate(dto.date());
        event.setStatus(dto.status());
        event.setEventType(dto.eventType());
        event.setDescription(dto.description());
        event.setEventPlayers(dto.eventPlayers());
        event.setEventTeams(dto.eventTeams());
        event.setLivestream(dto.livestream());
        event.setScores(dto.scores());
        event.setVenue(dto.venue());

        return event;
    }
}
