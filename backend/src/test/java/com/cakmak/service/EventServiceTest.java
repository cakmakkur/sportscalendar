package com.cakmak.service;

import com.cakmak.dtos.EventDto;
import com.cakmak.enums.EventStatus;
import com.cakmak.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cakmak.repository.EventRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

// here I used AI to show me correct file structure for junit tests,
// recap of junit syntax, naming conventions and annotations
// also taught me how to create a mock db
// I also used AI to debug a dependency bug, caused by version mismatch of test related dependencies

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    private static List<Event> mockEvents;

    @BeforeAll
    static void setup() {
        EventType eventType1 = new EventType(1L, "event1", new CompetitionType(), new EventCategory());
        EventType eventType2 = new EventType(2L, "event2", new CompetitionType(), new EventCategory());

        Venue venue = new Venue();
        Country country = new Country();
        venue.setCountry(country);

        mockEvents = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Event e = new Event();
            e.setId(String.valueOf(i));
            e.setDescription("Mock Event " + i);
            e.setDate(new Date());
            e.setEventType(i < 5 ? eventType1 : eventType2);
            e.setCreatedAt(new Date());
            e.setStatus(EventStatus.FUTURE);
            e.setVenue(venue);
            mockEvents.add(e);
        }
    }

    @Test
    void shouldContainTenEvents() {
        assertEquals(10, mockEvents.size());
    }

    @Test
    void shouldReturnEventById() {

        when(eventRepository.findEventById("1")).thenReturn(mockEvents.stream()
                .filter(e -> e.getId().equals("1"))
                .findFirst()
                .orElse(null));

        EventDto result = eventService.get("1");

        assertEquals("1", result.id());

        verify(eventRepository, times(1)).findEventById("1");
    }

    @Test
    void shouldReturnAllEvents() {
        when(eventRepository.findAllEvents()).thenReturn(mockEvents);

        List<EventDto> result = eventService.getAll();

        assertEquals(10, result.size());

        verify(eventRepository, times(1)).findAllEvents();
    }

    @Test
    void shouldReturnEventsByFilter() {


        OffsetDateTime start = OffsetDateTime.of(
                2025, 11, 7, 14, 30, 0, 0, ZoneOffset.UTC
        );
        OffsetDateTime end = OffsetDateTime.of(
                2025, 11, 7, 14, 30, 0, 0, ZoneOffset.UTC
        );

        when(eventRepository.getEventsByFilter(start, end, 1L, 1L)).thenReturn(mockEvents);


    }

    @Test
    void shouldSaveEvent() {

    }


}

