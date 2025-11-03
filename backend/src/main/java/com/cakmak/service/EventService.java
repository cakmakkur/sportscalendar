package com.cakmak.service;

import com.cakmak.dtos.*;
import com.cakmak.enums.EventStatus;
import com.cakmak.model.*;
import com.cakmak.repository.*;
import com.cakmak.util.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final VenueRepository venueRepository;
    private final CountryRepository countryRepository;

    public EventService(EventRepository eventRepository,
                        EventTypeRepository eventTypeRepository,
                        PlayerRepository playerRepository,
                        TeamRepository teamRepository,
                        VenueRepository venueRepository, CountryRepository countryRepository) {

        this.eventRepository = eventRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.venueRepository = venueRepository;
        this.countryRepository = countryRepository;
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

    public List<EventDto> getEventsByFilter (
            LocalDate date,
            Long eventType,
            Long country) {

        if (country == 0) {
            country = null;
        }

        if (eventType == 0) {
            eventType = null;
        }

        List<EventDto> dtos = new ArrayList<>();

        OffsetDateTime start = date.atStartOfDay().atOffset(ZoneOffset.UTC);
        OffsetDateTime end = start.plusDays(1);

        List<Event> events = eventRepository.getEventsByFilter(start, end, eventType, country);

        for(Event e : events) {
            dtos.add(Mapper.toEventDto(e));
        }

        return dtos;
    }

    @Transactional
    public void create(EventDto eventDto) {
        Event event = new Event();

        event.setDate(eventDto.date());

        if (eventDto.date().before(new Date())) {
            event.setStatus(EventStatus.FINISHED);
        } else if (eventDto.date().after(new Date())) {
            event.setStatus(EventStatus.FUTURE);
        } else {
            event.setStatus(EventStatus.LIVE);
        }

        EventType eventType = eventTypeRepository.findByEventId(eventDto.eventType().id());
        if (eventType == null) {
            throw new IllegalArgumentException("Invalid event type");
        }
        event.setEventType(eventType);
        event.setDescription(eventDto.description());

        // setting placeholder "online" country for new players and new teams
        // I didn't implement country selection in FE really...
        Country country = countryRepository.getCountryById(197L);

        if (!eventDto.players().get(0).lastname().isEmpty() && !eventDto.players().get(1).lastname().isEmpty()) {
            List<EventPlayer> eventPlayers = new ArrayList<>();
            for(PlayerDto dto : eventDto.players()) {
                Player p = playerRepository.findPlayerByFirstAndLastname(dto.firstname(), dto.lastname());

                if (p == null) {
                    Player newPlayer = new Player();
                    newPlayer.setFirstname(dto.firstname());
                    newPlayer.setLastname(dto.lastname());
                    newPlayer.setCountry(country);
                    p = newPlayer;
                    playerRepository.save(p);
                }

                EventPlayer eventPlayer = new EventPlayer();
                eventPlayer.setEvent(event);
                eventPlayer.setPlayer(p);
                eventPlayers.add(eventPlayer);
                p.addEventPlayer(eventPlayer);
            }
            event.setEventPlayers(eventPlayers);
        }

        if (!eventDto.teams().get(0).name().isEmpty() && !eventDto.teams().get(1).name().isEmpty()) {

            List<EventTeam> eventTeams = new ArrayList<>();
            for(TeamDto dto : eventDto.teams()) {

                Team t = teamRepository.findByTeamName(dto.name());

                if (t == null) {
                    Team newTeam = new Team();
                    newTeam.setName(dto.name());
                    newTeam.setCountry(country);
                    t = newTeam;
                    teamRepository.save(t);
                }

                EventTeam eventTeam = new EventTeam();
                eventTeam.setEvent(event);
                eventTeam.setTeam(t);
                eventTeams.add(eventTeam);
                t.addEventTeam(eventTeam);
            }
            event.setEventTeams(eventTeams);
        }

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

    public List<EventTypeDto> getEventTypes() {
        List<EventType> eventTypes = eventTypeRepository.findAll();
        List<EventTypeDto> dtos = new ArrayList<>();
        for(EventType e : eventTypes) {
            dtos.add(Mapper.toEventTypeDto(e));
        }
        return dtos;
    }

    public List<VenueDto> getVenues() {
        List<Venue> venues = venueRepository.findAll();
        List<VenueDto> dtos = new ArrayList<>();
        for(Venue v : venues) {
            dtos.add(Mapper.toVenueDto(v));
        }
        return dtos;
    }
}
