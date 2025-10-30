package com.cakmak.repository;

import com.cakmak.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, String> {

    @Query(value = "SELECT * FROM venues " +
            "WHERE id = :id", nativeQuery = true)
    Venue getById(String id);
}
