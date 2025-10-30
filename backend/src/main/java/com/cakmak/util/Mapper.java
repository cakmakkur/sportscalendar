package com.cakmak.util;

import com.cakmak.dtos.*;
import com.cakmak.enums.EventStatus;
import com.cakmak.model.*;

import java.util.ArrayList;
import java.util.List;


public class Mapper {

    public static EventCategoryDto toEventCategoryDto(EventCategory ec) {
        return new EventCategoryDto(
                ec.getId(),
                ec.getName(),
                ec.getDescription()
        );
    }

    public static EventTypeDto toEventTypeDto(EventType et) {
        return new EventTypeDto(
                et.getId(),
                et.getName(),
                toEventCategoryDto(et.getEventCategory()),
                et.getCompetitionType().toString()
        );
    }

    public static PlayerDto toPlayerDto(Player p ) {
        return new PlayerDto(
                p.getId(),
                p.getFirstname(),
                p.getLastname(),
                p.getCountry().getName()
        );
    }

    public static EventPlayerDto toEventPlayerDto(EventPlayer ep) {
        return new EventPlayerDto(
                ep.getId(),
                ep.getEvent().getId(),
                toPlayerDto(ep.getPlayer())
        );
    }

    public static TeamDto toTeamDto(Team t) {
        return new TeamDto(
                t.getId(),
                t.getName(),
                t.getCountry().getName()
        );
    }

    public static EventTeamDto toEventTeamDto(EventTeam et) {
        return new EventTeamDto(
                et.getId(),
                et.getTeam().getId(),
                et.getEvent().getId()
        );
    }

    public static VenueDto toVenueDto(Venue v) {
        return new VenueDto(
                v.getId(),
                v.getName(),
                v.getCountry().getName()
        );
    }

    public static ScoreDto toScoreDto(Score s) {

        String playerId = null;
        String teamId = null;

        if (s.getPlayer() != null) {
            playerId = s.getPlayer().getId();
        }

        if (s.getTeam() != null) {
            teamId = s.getTeam().getId();
        }

        return new ScoreDto(
                s.getId(),
                s.getEvent().getId(),
                playerId,
                teamId,
                s.getScore(),
                s.getScoredAt()
        );
    }

    public static LivestreamDto toLivestreamDto(Livestream l) {
        return new LivestreamDto(
                l.getId(),
                l.getUrl(),
                l.getMembershipRequired(),
                l.getPrice()
        );
    }

    public static EventDto toEventDto (Event e) {

        List<EventPlayerDto> eventPlayerDtos = new ArrayList<>();
        List<EventTeamDto> eventTeamDtos = new ArrayList<>();
        List<ScoreDto> scoreDtos = new ArrayList<>();

        for(EventPlayer ep : e.getEventPlayers()) {
            eventPlayerDtos.add(toEventPlayerDto(ep));
        }

        for(EventTeam et : e.getEventTeams()) {
            eventTeamDtos.add(toEventTeamDto(et));
        }

        for(Score s : e.getScores()) {
            scoreDtos.add(toScoreDto(s));
        }


        return new EventDto(
                e.getId(),
                e.getDate(),
                e.getCreatedAt(),
                toEventCategoryDto(e.getEventType().getEventCategory()),
                EventStatus.fromString(e.getStatus().toString()),
                toEventTypeDto(e.getEventType()),
                e.getDescription(),
                eventPlayerDtos,
                eventTeamDtos,
                toLivestreamDto(e.getLivestream()),
                scoreDtos,
                toVenueDto(e.getVenue())
        );
    }

}
