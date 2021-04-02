package com.example.demo.contoller;

import com.example.demo.entity.Lord;
import com.example.demo.service.LordService;
import com.example.demo.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lord")
public class LordController {
    private final LordService lordService;

    @PostMapping("/add")
    public Lord saveLord(@RequestBody Lord lord){
        return lordService.save(lord);
    }

    @GetMapping("/getLordNoPlanets")
    public List<Lord> getLordWithoutPlanet(){
        return lordService.getLordWithoutPlanet();
    }

    @GetMapping("/getYoungest")
    public List<Lord> getYoungestLords(){
        return lordService.getYoungestLords();
    }



    @PutMapping(("/rule"))
    public void ruleToPlanet(@RequestParam(value = "idPlanet", required=false) Long planetId, @RequestParam(value = "idLord", required=false) Long lordId ){
         lordService.ruleToPlanet(planetId, lordId);
    }

}
