package com.cakmak.service;

import com.cakmak.dtos.VenueDto;
import com.cakmak.model.Venue;
import com.cakmak.repository.VenueRepository;
import com.cakmak.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VenueService {

    private VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public List<VenueDto> getVenues() {
        List<Venue> venues = venueRepository.findAll();
        List<VenueDto> dtos = new ArrayList<>();
        for(Venue v : venues) {
            dtos.add(Mapper.toVenueDto(v));
        }
        return dtos;
    }
}
