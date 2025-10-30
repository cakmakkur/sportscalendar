package com.cakmak.service;

import com.cakmak.dtos.EventDto;
import com.cakmak.model.Event;
import com.cakmak.repository.EventRepository;
import com.cakmak.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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

    public void create(EventDto eventDto) {
        Event event = Mapper.toEvent(eventDto);
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
