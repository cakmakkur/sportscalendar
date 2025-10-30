package com.cakmak.service;

import com.cakmak.dtos.EventDto;
import com.cakmak.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventDto get(String id) {
        return null;
    }

    public List<EventDto> getAll() {
        return null;
    }

    public EventDto create(String id) {
        return null;
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
