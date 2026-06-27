package com.airport.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.api.model.Airport;
import com.airport.api.model.City;
import com.airport.api.repository.AirportRepository;
import com.airport.api.repository.CityRepository;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    
    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public AirportController(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }

    //GET with pagination
    @GetMapping
    public Page<Airport> getAllAirports(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    //POST
    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {

    if (airport.getCity() != null && airport.getCity().getId() != null) {
            City city = cityRepository.findById(airport.getCity().getId())
                .orElseThrow(() -> new RuntimeException("City not found"));

            airport.setCity(city);
        }
        
        return airportRepository.save(airport);
    }

    //PUT
    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable int id, @RequestBody Airport airport) {

        if (!airportRepository.existsById(id)) {
            throw new RuntimeException("Airport with id " + id + " does not exist.");
        }

        airport.setId(id);
        return airportRepository.save(airport);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable int id) {

        if (!airportRepository.existsById(id)) {
            throw new RuntimeException("Airport with id " + id + " does not exist.");
        }

        airportRepository.deleteById(id);
    }

}