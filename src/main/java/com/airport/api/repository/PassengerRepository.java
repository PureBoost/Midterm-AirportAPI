package com.airport.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airport.api.model.Passenger;


public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
