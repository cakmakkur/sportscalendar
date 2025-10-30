package com.cakmak.repository;

import com.cakmak.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    @Query(value = "SELECT * FROM events " +
            "WHERE id = :id", nativeQuery = true
    )
    Event findEventById(@Param("id") String id);

    @Query(value = "SELECT * FROM events", nativeQuery = true)
    List<Event> findAllEvents();

}
