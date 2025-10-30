package com.cakmak.repository;

import com.cakmak.model.Livestream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivestreamRepository extends JpaRepository<Livestream, String> {
}
