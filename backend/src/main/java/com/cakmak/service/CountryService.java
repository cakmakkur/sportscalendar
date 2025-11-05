package com.cakmak.service;

import com.cakmak.dtos.CountryDto;
import com.cakmak.model.Country;
import com.cakmak.repository.CountryRepository;
import com.cakmak.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /*
     * returns dtos of all countries
     * */
    public List<CountryDto> getCountries() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDto> dtos = new ArrayList<>();
        for(Country c : countries) {
            dtos.add(Mapper.toCountryDto(c));
        }
        return dtos;
    }
}
