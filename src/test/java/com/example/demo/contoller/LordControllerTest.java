package com.example.demo.contoller;

import com.example.demo.entity.Lord;
import com.example.demo.repository.LordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LordControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LordRepository lordRepository;

    /*
    @MockBean
    private PlanetRepository planetRepository;
    @Mock
    private Planet planet = new Planet();
     */

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void saveLord() throws Exception {
       Lord testLord = createTestLord("Ciri", 15, 1);

       Mockito.when(lordRepository.save(testLord)).thenReturn(testLord);
        mvc.perform(
                post("/lord/add")
                        .content(objectMapper.writeValueAsString(testLord))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testLord)));
    }

    @Test
    void getLordWithoutPlanet() throws Exception {
        mvc.perform(
                get("/lord/getLordNoPlanets"))
                .andExpect(status().isOk());
    }


    @Test
    void getYoungestLords() throws Exception {
        mvc.perform(
                get("/lord/getYoungest"))
                .andExpect(status().isOk());
    }


    public static Lord createTestLord(String name, int age, long id){
        Lord lord = new Lord();
        lord.setName(name);
        lord.setAge(age);
        lord.setId(id);

        return lord;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    @Test
    void ruleToPlanet() throws Exception {
       mvc.perform(
               put("/lord/rule")
               .param("idPlanet", "1")
               .param("idLord", "10"))
               .andExpect(status().isOk());

    }
     */


}