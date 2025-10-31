package com.cakmak.service;

import com.cakmak.dtos.EventDto;
import com.cakmak.dtos.ScoreDto;
import com.cakmak.model.*;
import com.cakmak.repository.*;
import com.cakmak.util.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final VenueRepository venueRepository;

    public EventService(EventRepository eventRepository,
                        EventTypeRepository eventTypeRepository,
                        PlayerRepository playerRepository,
                        TeamRepository teamRepository,
                        VenueRepository venueRepository) {

        this.eventRepository = eventRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.venueRepository = venueRepository;
    }

    public EventDto get(String id) {
        Event event = this.eventRepository.findEventById(id);

        if (event == null) {
            throw new IllegalArgumentException("Event not found");
        }

        return Mapper.toEventDto(event);
    }

    public List<EventDto> getAll() {
        List<Event> events = this.eventRepository.findAllEvents();

        List<EventDto> dtos = new ArrayList<>();
        for(Event e : events) {
            dtos.add(Mapper.toEventDto(e));
        }

        return dtos;
    }

    @Transactional
    public void create(EventDto eventDto) {
        Event event = new Event();

        event.setDate(eventDto.date());
        event.setStatus(eventDto.status());

        EventType eventType = eventTypeRepository.findByEventId(eventDto.eventType().id());
        if (eventType == null) {
            throw new IllegalArgumentException("Invalid event type");
        }
        event.setEventType(eventType);
        event.setDescription(eventDto.description());

        List<EventPlayer> eventPlayers = new ArrayList<>();
        for(String pid : eventDto.playerIds()) {
            Player p = playerRepository.findPlayerById(pid);

            EventPlayer eventPlayer = new EventPlayer();
            eventPlayer.setEvent(event);
            eventPlayer.setPlayer(p);
            eventPlayers.add(eventPlayer);
            p.addEventPlayer(eventPlayer);
        }
        event.setEventPlayers(eventPlayers);

        List<EventTeam> eventTeams = new ArrayList<>();
        for(String teamId : eventDto.teamIds()) {
            Team t = teamRepository.findTeamById(teamId);

            EventTeam eventTeam = new EventTeam();
            eventTeam.setEvent(event);
            eventTeam.setTeam(teamRepository.findTeamById(teamId));
            eventTeams.add(eventTeam);
            t.addEventTeam(eventTeam);
        }
        event.setEventTeams(eventTeams);

        if (eventDto.livestream() != null) {
            Livestream livestream = new Livestream();
            livestream.setEvent(event);
            livestream.setMembershipRequired(eventDto.livestream().membershipRequired());
            livestream.setPrice(eventDto.livestream().price());
            livestream.setUrl(eventDto.livestream().url());
            event.setLivestream(livestream);
        }

        List<Score> scores = new ArrayList<>();
        for(ScoreDto s : eventDto.scores()) {
            Score score = new Score();
            score.setEvent(event);
            score.setScore(s.score());
            score.setPlayer(playerRepository.findPlayerById(s.playerId()));
            score.setTeam(teamRepository.findTeamById(s.teamId()));
            scores.add(score);
        }
        event.setScores(scores);

        Venue venue = venueRepository.findVenueById(eventDto.venue().id());
        venue.addEvent(event);
        event.setVenue(venue);

        eventRepository.save(event);
    }

    public EventDto delete(String id) {
        // placeholder. not needed in the requirements of the task
        return null;
    }

    public EventDto update(String id) {
        // placeholder. not needed in the requirements of the task
        return null;
    }

}
