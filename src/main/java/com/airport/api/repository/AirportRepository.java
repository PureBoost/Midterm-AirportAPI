package com.airport.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airport.api.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    
}
