package com.cakmak.controller;

import com.cakmak.dtos.CountryDto;
import com.cakmak.dtos.EventDto;
import com.cakmak.dtos.EventTypeDto;
import com.cakmak.dtos.VenueDto;
import com.cakmak.repository.CountryRepository;
import com.cakmak.service.EventService;
import com.cakmak.util.Mapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;
    private final CountryRepository countryRepository;

    public EventController(EventService eventService, CountryRepository countryRepository) {
        this.eventService = eventService;
        this.countryRepository = countryRepository;
    }

    /*
    * Takes filter as url parameter and returns the events
    * */
    @GetMapping
    public ResponseEntity<List<EventDto>> getEventsByFilter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Long eventType,
            @RequestParam(required = false) Long country) {
        List<EventDto> events = eventService.getEventsByFilter(
                date,
                eventType,
                country);
        return ResponseEntity.ok(events);
    }

    /*
     * Returns single event by id
     * */
    @GetMapping("/get/{id}")
    public ResponseEntity<EventDto> get (@PathVariable String id) {
        EventDto dto = eventService.get(id);
        return ResponseEntity.ok(dto);
    }

    /*
     * Returns all events
     * */
    @GetMapping("/get-all")
    public ResponseEntity<List<EventDto>> getAll () {
        List<EventDto> dtos = eventService.getAll();
        return ResponseEntity.ok(dtos);
    }

    /*
     * Takes EventDto and creates a new entry in the db
     * */
    @PostMapping("/create")
    public ResponseEntity<Void> create (@RequestBody EventDto eventDto) {
        eventService.create(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
     * returns available event types.
     * */
    @GetMapping("/types")
    public ResponseEntity<List<EventTypeDto>> getTypes() {
        return ResponseEntity.ok(eventService.getEventTypes());
    }

    /*
     * placeholder update endpoint. not needed in the requirements of the task
     * */
    @PutMapping("/update")
    public ResponseEntity<Void> update (@PathVariable String id, @RequestBody EventDto eventDto) {
        return null;
    }

    /*
     * placeholder delete endpoint. not needed in the requirements of the task
     * */
    @DeleteMapping ("/delete")
    public ResponseEntity<Void> delete (@PathVariable String id) {
        // placeholder. not needed in the requirements of the task
        return null;
    }

}
