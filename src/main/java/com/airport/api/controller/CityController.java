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

import com.airport.api.model.City;
import com.airport.api.repository.CityRepository;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    //GET
    @GetMapping
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    //POST
    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityRepository.save(city);

    }

    //PUT
    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        City city = cityRepository.findById(id).orElse(null);

        if (city == null) {
            throw new RuntimeException("City not found");
        }
        
        city.setName(updatedCity.getName());
        city.setState(updatedCity.getState());
        city.setPopulation(updatedCity.getPopulation());

        return cityRepository.save(city);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {

        if (!cityRepository.existsById(id)) {
            throw new RuntimeException("City not found");
        }
        
        cityRepository.deleteById(id);
    }

}