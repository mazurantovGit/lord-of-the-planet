package com.example.demo.service;

import com.example.demo.entity.Planet;
import com.example.demo.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetService {
    private final PlanetRepository planetRepository;

    public List<Planet> getAllPlanets(){
        return planetRepository.findAll();
    }

    public Planet save(Planet planet){
        return  planetRepository.save(planet);

    }

    public List<Planet> getFreePlanet(){
        return planetRepository.getAllByLordOfPlanetIsNull();
    }

    public void destroy(Long id){
        planetRepository.deleteById(id);
    }
}
