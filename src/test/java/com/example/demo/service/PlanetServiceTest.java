package com.example.demo.service;

import com.example.demo.entity.Planet;
import com.example.demo.repository.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PlanetServiceTest {
    @Mock
    private PlanetRepository planetRepository;

    private PlanetService planetService;

    private Planet planet;

    @BeforeEach
    void setUp(){
        planetService = new PlanetService(planetRepository);
        planet = new Planet();
        planet.setId(1);
        planet.setName("Titan");

    }

    @Test
    void save() {
        Mockito.when(planetRepository.save(planet)).thenReturn(planet);
        Planet testPlanet = planetService.save(planet);
        assertEquals("Titan", testPlanet.getName());
        Mockito.verify(planetRepository, Mockito.times(1)).save(testPlanet);

    }

    @Test
    void destroy() {
        Long id = planet.getId();
        Mockito.doNothing().when(planetRepository).delete(planet);
        planetService.destroy(id);
        Mockito.verify(planetRepository, Mockito.times(1)).deleteById(id);
    }
}