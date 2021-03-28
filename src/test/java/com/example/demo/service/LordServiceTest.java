package com.example.demo.service;

import com.example.demo.entity.Lord;
import com.example.demo.entity.Planet;
import com.example.demo.repository.LordRepository;
import com.example.demo.repository.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LordServiceTest {

   @Mock
   private LordRepository lordRepository;

   @Mock
   private PlanetRepository planetRepository;

   private LordService lordService;

   private Lord lord;

  private Planet planet;

    @BeforeEach
    void setUp(){
        lordService = new LordService(lordRepository, planetRepository);
        lord = new Lord();
        lord.setAge(20);
        lord.setId(10);
        lord.setName("Isaac Clarke");
        lord.setListPlanets(new ArrayList<>());

        planet = new Planet();
        planet.setId(10);
        planet.setName("Titan");


    }

    @Test
    void savedLord(){
        Mockito.lenient().when(lordRepository.save(lord)).thenReturn(lord);
        Lord testLord = lordService.save(lord);
        assertEquals("Isaac Clarke", testLord.getName());
        Mockito.verify(lordRepository, Mockito.times(1)).save(testLord);
    }


    @Test
    void ruleToPlanet_AddingPlanetToLord() {
        Mockito.when(lordRepository.save(lord)).thenReturn(lord);
        Mockito.when(planetRepository.save(planet)).thenReturn(planet);

        Mockito.when(planetRepository.getById(planet.getId())).thenReturn(planet);
        Mockito.when(lordRepository.getById(lord.getId())).thenReturn(lord);

        lordService.ruleToPlanet(planet.getId(), lord.getId());
        assertEquals("Isaac Clarke", planet.getLordOfPlanet().getName());
        

    }

    @Test
    void ruleToPlanet_NotAddingPlanetToLord() {
        Lord lWithPlanet = new Lord();
        lWithPlanet.setName("Jojk");
        lWithPlanet.setAge(152);

        Planet pWithLord = new Planet();
        pWithLord.setName("Earth");
        pWithLord.setLordOfPlanet(lWithPlanet);
        lWithPlanet.setListPlanets(Collections.singletonList(pWithLord));

        Mockito.when(planetRepository.getById(pWithLord.getId())).thenReturn(pWithLord);
        RuntimeException ex = assertThrows(RuntimeException.class,()->lordService.ruleToPlanet(pWithLord.getId(), lWithPlanet.getId()));
        assertEquals("У этой планеты уже есть хозяин!", ex.getMessage());

    }



}



