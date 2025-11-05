package com.cakmak.service;

import com.cakmak.dtos.*;
import com.cakmak.enums.EventStatus;
import com.cakmak.model.*;
import com.cakmak.repository.*;
import com.cakmak.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*
    * Returns dto of the single event by id
    * */
    public EventDto get(String id) {
        Event event = this.eventRepository.findEventById(id);

        if (event == null) {
            throw new IllegalArgumentException("Event not found");
        }

        return Mapper.toEventDto(event);
    }

    /*
     * Returns dtos of all events
     * */
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

    /*
     * Saves a new event into the DB
     * */
    @Transactional
    public void create(EventDto eventDto) {
        Event event = new Event();

        if (eventDto.date() == null) {
            throw new IllegalArgumentException("Event date is required");
        }
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

        // setting fixed "online" country for new players and new teams
        // I didn't implement country selection in FE, since there is no create-player etc functionality required
        Country country = countryRepository.getCountryById(197L);

        //  fails validation if neither team info nor player info is given,
        if (eventDto.players() == null && eventDto.teams() == null) {
            throw new IllegalArgumentException("Either player info or team info is required");
        }

        eventRepository.saveAndFlush(event);

        // if eventDto includes players (== CompetitionType.players)
        if (eventDto.players() != null
                && !eventDto.players().get(0).lastname().isEmpty()
                && !eventDto.players().get(1).lastname().isEmpty()) {
            List<EventPlayer> eventPlayers = new ArrayList<>();
            for(PlayerDto dto : eventDto.players()) {
                Player p = playerRepository.findPlayerByFirstAndLastname(dto.firstname(), dto.lastname());

                // if the player doesn't exist in the db, create/save new player
                if (p == null) {
                    Player newPlayer = new Player();
                    newPlayer.setFirstname(dto.firstname());
                    newPlayer.setLastname(dto.lastname());
                    newPlayer.setCountry(country);
                    p = newPlayer;
                    playerRepository.save(p);
                }

                // register the player to the event
                EventPlayer eventPlayer = new EventPlayer();
                eventPlayer.setEvent(event);
                eventPlayer.setPlayer(p);
                eventPlayers.add(eventPlayer);
                p.addEventPlayer(eventPlayer);
            }
            event.setEventPlayers(eventPlayers);
        }

        // if eventDto includes teams (== CompetitionType.teams)
        if (eventDto.teams() != null
                && !eventDto.teams().get(0).name().isEmpty()
                && !eventDto.teams().get(1).name().isEmpty()) {

            List<EventTeam> eventTeams = new ArrayList<>();
            for(TeamDto dto : eventDto.teams()) {

                Team t = teamRepository.findByTeamName(dto.name());

                // if the team doesn't exist in the db, create/save new team
                if (t == null) {
                    Team newTeam = new Team();
                    newTeam.setName(dto.name());
                    newTeam.setCountry(country);
                    t = newTeam;
                    teamRepository.save(t);
                }

                // register the team to the event
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

        // frontend doesn't implement the functionality to add scores to the event...
        // this can however be used with curl or swagger
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
        if (venue == null) {
            throw new EntityNotFoundException("Venue not found: " + eventDto.venue().id());
        }
        venue.addEvent(event);
        event.setVenue(venue);

        eventRepository.save(event);
        System.out.println("saved event: " + event);
    }

    /*
    * placeholder. not needed in the requirements of the task
    * */
    public EventDto delete(String id) {
        return null;
    }

    /*
     * placeholder. not needed in the requirements of the task
     * */
    public EventDto update(String id) {
        return null;
    }

    /*
    * returns dtos of the available event types
    * */
    public List<EventTypeDto> getEventTypes() {
        List<EventType> eventTypes = eventTypeRepository.findAll();
        List<EventTypeDto> dtos = new ArrayList<>();
        for(EventType e : eventTypes) {
            dtos.add(Mapper.toEventTypeDto(e));
        }
        return dtos;
    }


}
