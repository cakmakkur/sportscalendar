package com.cakmak.controller;

import com.cakmak.dtos.VenueDto;
import com.cakmak.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/venue")
public class VenueController {


    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @Operation(summary = "Get all venues", description = "Retrieves a list of all available venues")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of venues retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<VenueDto>> getVenues() {
        return ResponseEntity.ok(venueService.getVenues());
    }
}
