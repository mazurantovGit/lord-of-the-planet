package com.example.demo.contoller;

import com.example.demo.entity.Planet;
import com.example.demo.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planet")
public class PlanetController {
    private final PlanetService planetService;

    @PostMapping("/add")
    public Planet savePlanet(@RequestBody Planet planet){
        return planetService.save(planet);
    }

    @DeleteMapping("/destroy/{idPlanet}")
    public void destroyPlanet(@PathVariable(value = "idPlanet") Long id){ planetService.destroy(id); }


}
