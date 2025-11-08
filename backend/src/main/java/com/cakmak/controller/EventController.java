package com.cakmak.controller;

import com.cakmak.dtos.EventDto;
import com.cakmak.dtos.EventTypeDto;
import com.cakmak.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Retrieves a list of events filtered by date, event type, and country.
     *
     * @param date      the date of the events to retrieve, formatted as ISO date (yyyy-MM-dd)
     * @param eventType (optional) the ID of the event type to filter by
     * @param country   (optional) the ID of the country to filter events by
     * @return a ResponseEntity containing a list of EventDto objects matching the filter criteria
     */
    @Operation(summary = "Get filtered events", description = "Retrieves events filtered by date, event type, and country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved filtered events"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
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

    /**
     * Retrieves a single event by its ID.
     *
     * @param id the ID of the event to retrieve
     * @return a ResponseEntity containing the EventDto corresponding to the given ID
     */
    @Operation(summary = "Get event by ID", description = "Retrieves a single event by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the event"),
            @ApiResponse(responseCode = "404", description = "Event with the given ID not found")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<EventDto> get (@PathVariable String id) {
        EventDto dto = eventService.get(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Get all events", description = "Retrieves a list of all events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of events")
    })
    @GetMapping("/get-all")
    public ResponseEntity<List<EventDto>> getAll () {
        List<EventDto> dtos = eventService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Create a new event", description = "Creates a new event with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    public ResponseEntity<Void> create (@RequestBody EventDto eventDto) {
        eventService.create(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all event types", description = "Retrieves a list of all available event types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved event types")
    })
    @GetMapping("/types")
    public ResponseEntity<List<EventTypeDto>> getTypes() {
        return ResponseEntity.ok(eventService.getEventTypes());
    }

    /*
    * PLACEHOLDER
    * */
    @Operation(summary = "Update an event", description = "Updates an existing event by its ID with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event updated successfully"),
            @ApiResponse(responseCode = "404", description = "Event with the specified ID not found")
    })
    @PutMapping("/update")
    public ResponseEntity<Void> update (@PathVariable String id, @RequestBody EventDto eventDto) {
        return null;
    }

    /**
     * PLACEHOLDER
     */
    @Operation(summary = "Delete an event", description = "Deletes an existing event by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Event with the specified ID not found")
    })
    @DeleteMapping ("/delete")
    public ResponseEntity<Void> delete (@PathVariable String id) {
        return null;
    }

}
