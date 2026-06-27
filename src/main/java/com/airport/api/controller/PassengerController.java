package com.airport.api.controller;

import java.util.ArrayList;
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
import com.airport.api.model.Passenger;
import com.airport.api.repository.AircraftRepository;
import com.airport.api.repository.PassengerRepository;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerRepository passengerRepository;
    private final AircraftRepository aircraftRepository;

    public PassengerController(PassengerRepository passengerRepository, AircraftRepository aircraftRepository) {
        this.passengerRepository = passengerRepository;
        this.aircraftRepository = aircraftRepository;
    }

    //GET
    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    //POST
    @PostMapping
        public Passenger createPassenger(@RequestBody Passenger passenger) {

            if (passenger.getAircraft() != null) {

                List<Aircraft> fullAircraftList = new ArrayList<>();

                for (Aircraft a : passenger.getAircraft()) {
                    Aircraft fullAircraft = aircraftRepository.findById(a.getId())
                        .orElseThrow(() -> new RuntimeException("Aircraft not found: " + a.getId()));

                    fullAircraftList.add(fullAircraft);
                }

                passenger.setAircraft(fullAircraftList);
            }

        return passengerRepository.save(passenger);
    }

    //PUT
    @PutMapping("/{id}")
    public Passenger updatePassenger(@PathVariable int id, @RequestBody Passenger updatedPassenger) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);

        if (passenger == null) {
            throw new RuntimeException("Passenger not found");
        }

        passenger.setFirstName(updatedPassenger.getFirstName());
        passenger.setLastName(updatedPassenger.getLastName());
        passenger.setPhoneNumber(updatedPassenger.getPhoneNumber());

        return passengerRepository.save(passenger);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable int id) {

        if (!passengerRepository.existsById(id)) {
            throw new RuntimeException("Passenger not found");
        }

        passengerRepository.deleteById(id);
    }
    
}
