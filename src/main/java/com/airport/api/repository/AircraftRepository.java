package com.airport.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airport.api.model.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {

}
