package com.example.demo.service;

import com.example.demo.entity.Planet;
import com.example.demo.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanetService {
    private final PlanetRepository planetRepository;

    public Planet save(Planet planet){
        return  planetRepository.save(planet);

    }

    public void destroy(Long id){
        planetRepository.deleteById(id);
    }
}
