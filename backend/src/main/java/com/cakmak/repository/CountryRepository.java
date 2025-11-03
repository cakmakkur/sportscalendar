package com.cakmak.repository;

import com.cakmak.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country getCountryById(Long id);
}
