package com.cakmak.repository;

import com.cakmak.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    @Query(value = "SELECT * FROM teams " +
            "WHERE id = :teamId", nativeQuery = true)
    Team findTeamById(String teamId);
}
