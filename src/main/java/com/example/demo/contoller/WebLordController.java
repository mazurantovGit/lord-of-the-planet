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
@RequestMapping("/lords")
public class WebLordController {

    private final LordService lordService;

    private final PlanetService planetService;

    @GetMapping
    public String showAllLords(Model model){
        Lord lord = new Lord();
        model.addAttribute("lords", lordService.getAllLords());
        model.addAttribute("lord", lord);

        Planet planet = new Planet();
        model.addAttribute("planets", planetService.getFreePlanet());
        model.addAttribute("planet", planet);

        return "/lords";
    }

    @GetMapping("/lonelylords")
    public String showLonelyLords(Model model){
        Lord lord = new Lord();
        model.addAttribute("lords", lordService.getLordWithoutPlanet());
        model.addAttribute("lord", lord);
        return "/lords-filter";
    }

    @GetMapping("/youngestlords")
    public String showYoungestLords(Model model){
        Lord lord = new Lord();
        model.addAttribute("lords", lordService.getYoungestLords());
        model.addAttribute("lord", lord);
        return "/lords-filter";
    }

    @PostMapping("/add")
    public String addLord(@ModelAttribute(value = "lord")Lord lord) {
        lordService.save(lord);
        return "redirect:/lords";
    }

    @PostMapping("/rule/{lordId}")
    public String ruleToPlanet(@RequestParam Long planet, @PathVariable(value = "lordId")Long lordId){
        lordService.ruleToPlanet(planet, lordId);
        return "redirect:/lords";
    }
}
