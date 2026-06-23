package com.airport.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airport.api.model.City;

//Interface creates a repository called CityRepository that manages City objects whose IDs are Longs. JpaRepository tells Spring this repository works with City entitiess so spring automatically provides standard CRUD database operations
public interface CityRepository extends JpaRepository<City, Integer> {
    
}
