package com.example.demo.service;
import java.util.List;
import com.example.demo.entity.Lord;
import com.example.demo.entity.Planet;
import com.example.demo.repository.LordRepository;
import com.example.demo.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LordService {
    private final LordRepository lordRepository;
    private final PlanetRepository planetRepository;

    public Lord save(Lord lord){
        return lordRepository.save(lord);
    }

    public List<Lord> getLordWithoutPlanet(){
        return lordRepository.getAllByListPlanetsIsNull();
    }


    public List<Lord> getYoungestLords(){
        return lordRepository.findTop10ByOrderByAge();
    }

    public void ruleToPlanet(Long planetId, Long lordId){
        Planet planet = planetRepository.getById(planetId);
        if(planet.getLordOfPlanet() == null){
            Lord lord = lordRepository.getById(lordId);
            lord.addToListPlanet(planet);
            planet.setLordOfPlanet(lord);
            planetRepository.save(planet);
        }else{
            throw new RuntimeException("У этой планеты уже есть хозяин!");
        }

    }
}
