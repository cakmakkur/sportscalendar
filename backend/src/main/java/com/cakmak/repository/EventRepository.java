package com.cakmak.repository;

import com.cakmak.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    @Query(value = "SELECT * FROM events " +
            "WHERE id = :id", nativeQuery = true
    )
    Event findEventById(@Param("id") String id);

    @Query(value = "SELECT * FROM events", nativeQuery = true)
    List<Event> findAllEvents();

    @Query(value =
        "SELECT e.* " +
        "FROM events e " +
        "LEFT JOIN event_types et ON e.event_type = et.id " +
        "LEFT JOIN venues v ON e.venue = v.id " +
        "WHERE e.\"date\" >= :start " +
        "AND e.\"date\" < :end " +
        "AND (:eventType IS NULL OR e.event_type = :eventType) " +
        "AND (:country IS NULL OR v.country = :country)",
        nativeQuery = true
    )
    List<Event> getEventsByFilter(
            @Param("start") OffsetDateTime start,
            @Param("end") OffsetDateTime end,
            @Param("eventType") Long eventType,
            @Param("country") Long country
    );
}
