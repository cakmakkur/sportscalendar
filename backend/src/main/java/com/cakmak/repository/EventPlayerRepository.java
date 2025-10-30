package com.cakmak.repository;

import com.cakmak.model.EventPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPlayerRepository extends JpaRepository<EventPlayer, String> {

    @Query(value = "SELECT * from event_player " +
                  "WHERE event = :evendId", nativeQuery = true)
    EventPlayer findByEventId(String eventId);

    @Query(value = "SELECT * from event_player " +
            "WHERE player = :playerId", nativeQuery = true)
    EventPlayer findByPlayerId(String playerId);
}
