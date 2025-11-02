package com.cakmak.controller;


import com.cakmak.dtos.EventTypeDto;
import com.cakmak.model.EventType;
import com.cakmak.repository.EventTypeRepository;
import com.cakmak.util.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/eventtypes")
public class EventTypesController {

    private final EventTypeRepository eventTypeRepository;

    public EventTypesController(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    public List<EventTypeDto> get () {
        List<EventTypeDto> eventTypeDtos = new ArrayList<>();
        List<EventType> eventTypes = eventTypeRepository.findAll();
        for(EventType et : eventTypes) {
            eventTypeDtos.add(Mapper.toEventTypeDto(et));
        }
        return eventTypeDtos;
    }
}
