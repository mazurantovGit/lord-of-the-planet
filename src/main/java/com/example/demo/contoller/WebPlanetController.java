package com.example.demo.contoller;

import com.example.demo.entity.Lord;
import com.example.demo.entity.Planet;
import com.example.demo.service.LordService;
import com.example.demo.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/planets")
public class WebPlanetController {

    private final PlanetService planetService;

    @GetMapping
    public String showAllPlanets(Model model){
        Planet planet = new Planet();
        model.addAttribute("planets", planetService.getAllPlanets());
        model.addAttribute("planet", planet);
        return "/planets";
    }

    @GetMapping("/lonelylords")
    public String showLonelyLords(Model model){
        Planet planet = new Planet();
        model.addAttribute("planets", planetService.getAllPlanets());
        model.addAttribute("planet", planet);
        return "/planets";
    }

    @PostMapping("/add")
    public String addPlanet(@ModelAttribute(value = "planet")Planet planet) {
        planetService.save(planet);
        return "redirect:/planets";
    }


    @PostMapping("/delete/{planetId}")
    public String destroyPlanet(@PathVariable(value = "planetId")Long planetId){
        planetService.destroy(planetId);
        return "redirect:/planets";
    }


}