package com.airport.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.api.model.Aircraft;
import com.airport.api.repository.AircraftRepository;

@RestController
@RequestMapping("/api/aircrafts")
public class AircraftController {

    private final AircraftRepository aircraftRepository;
    

    public AircraftController(AircraftRepository aircraftRepository) {
    this.aircraftRepository = aircraftRepository;
}

    //GET
    @GetMapping
    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    //POST
    @PostMapping
    public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
}

    //PUT
    @PutMapping("/{id}")
    public Aircraft updateAircraft(@PathVariable int id, @RequestBody Aircraft aircraft) {

        if (!aircraftRepository.existsById(id)) {
            throw new RuntimeException("Aircraft with id " + id + " does not exist.");
        }

        aircraft.setId(id);
        return aircraftRepository.save(aircraft);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteAircraft(@PathVariable int id) {

        if (!aircraftRepository.existsById(id)) {
            throw new RuntimeException("Aircraft with id " + id + " does not exist.");
        }

        aircraftRepository.deleteById(id);
    }
    
}
