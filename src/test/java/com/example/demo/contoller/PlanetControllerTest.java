package com.example.demo.contoller;

import com.example.demo.entity.Planet;
import com.example.demo.repository.PlanetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlanetControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlanetRepository planetRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void savePlanet() throws Exception {
        Planet testPlanet = new Planet();
        testPlanet.setName("Mars");

        Mockito.when(planetRepository.save(testPlanet)).thenReturn(testPlanet);
        mvc.perform(
                post("/planet/add")
                        .content(objectMapper.writeValueAsString(testPlanet))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testPlanet)));
    }

    @Test
    void destroyPlanet() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/planet/{idPlanet}", 1) )
                .andExpect(status().isOk());
    }
}