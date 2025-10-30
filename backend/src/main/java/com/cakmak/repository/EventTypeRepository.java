package com.cakmak.repository;

import com.cakmak.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {

    @Query(value = "SELECT * FROM event_types " +
            "WHERE id = :id", nativeQuery = true)
    EventType findByEventId(Long id);
}
