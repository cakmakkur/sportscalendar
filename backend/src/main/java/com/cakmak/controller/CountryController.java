package com.cakmak.controller;

import com.cakmak.dtos.CountryDto;
import com.cakmak.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get all countries", description = "Retrieves a list of all available countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of countries retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<CountryDto>> getCountries() {
        return ResponseEntity.ok(countryService.getCountries());
    }
}
