package com.cakmak.repository;

import com.cakmak.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    @Query(value = "SELECT * FROM players " +
            "WHERE id = :playerId", nativeQuery = true)
    Player findPlayerById(String playerId);

    @Query(value = "SELECT * FROM players " +
            "WHERE firstname = :firstname " +
            "AND lastname = :lastname", nativeQuery = true)
    Player findPlayerByFirstAndLastname(String firstname, String lastname);
}


