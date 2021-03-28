package com.example.demo.service;

import com.example.demo.entity.Lord;
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

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LordServiceTest {

   @Mock
   private LordRepository lordRepository;

   @Mock
   private PlanetRepository planetRepository;

   private LordService lordService;

   private Lord lord;

  // private Planet planet;

    @BeforeEach
    void setUp(){
        lordService = new LordService(lordRepository, planetRepository);
        lord = new Lord();
        lord.setAge(20);
        lord.setId(10);
        lord.setName("Isaac Clarke");

        /*
        planet = new Planet();
        planet.setId(10);
        planet.setName("Titan");
         */

    }

    @Test
    void savedLord(){
        Mockito.lenient().when(lordRepository.save(lord)).thenReturn(lord);
        Lord testLord = lordService.save(lord);
        assertEquals("Isaac Clarke", testLord.getName());
        Mockito.verify(lordRepository, Mockito.times(1)).save(testLord);
    }

 /*
    @Test
    void ruleToPlanet() {
        Mockito.lenient().when(lordRepository.save(lord)).thenReturn(lord);
        Lord testLord = lordRepository.save(lord);

        Mockito.lenient().when(planetRepository.save(planet)).thenReturn(planet);
        Planet testPlanet = planetRepository.save(planet);


        Mockito.lenient().when(lordService.ruleToPlanet(testPlanet.getId(), testLord.getId())).thenReturn(true);
        assertTrue(lordService.ruleToPlanet(testPlanet.getId(), testLord.getId()));
        Mockito.verify(lordService, Mockito.times(1)).ruleToPlanet(testPlanet.getId(),testLord.getId());

      //  lordService.ruleToPlanet(testPlanet.getId(), testLord.getId());
      //  assertEquals("Isaac Clarke", testPlanet.getLordOfPlanet().getName());
    }

    /*
    @Test
    void getYoungestLords() {

    }

 */
}



