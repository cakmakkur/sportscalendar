package com.cakmak.controller;

import com.cakmak.dtos.EventDto;
import com.cakmak.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EventDto> get (@PathVariable String id) {
        EventDto dto = eventService.get(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<EventDto>> getAll (@PathVariable String id) {
        List<EventDto> dtos = eventService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create (@RequestBody EventDto eventDto) {
        eventService.create(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update (@PathVariable String id,
                                        @RequestBody EventDto eventDto) {
        // placeholder. not needed in the requirements of the task
        return null;
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> delete (@PathVariable String id) {
        // placeholder. not needed in the requirements of the task
        return null;
    }
}
