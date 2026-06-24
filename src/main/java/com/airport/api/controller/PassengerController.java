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

import com.airport.api.model.Passenger;
import com.airport.api.repository.PassengerRepository;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerRepository passengerRepository;

    public PassengerController(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    //GET
    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    //POST
    @PostMapping
    public Passenger createPassenger(@RequestBody Passenger passenger) {
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
