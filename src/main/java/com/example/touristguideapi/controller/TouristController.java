package com.example.touristguideapi.controller;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private TouristService touristService;

    @Autowired
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    public ResponseEntity<List<TouristAttraction>> getAllAttractions() {
        List<TouristAttraction> attractions = touristService.getAllTouristAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name) {
        TouristAttraction attraction = touristService.getTouristAttractionByName(name);
        if (attraction != null) {
            return new ResponseEntity<>(attraction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return new ResponseEntity<>("Attraction has been added!" , HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateAttraction(@RequestParam String name, @RequestBody TouristAttraction updatedAttraction) {
        if (touristService.getTouristAttractionByName(name) == null) {
            return new ResponseEntity<>(name + " could not be found" , HttpStatus.NOT_FOUND);
        }
        touristService.updateTouristAttraction(name, updatedAttraction);
        return new ResponseEntity<>(name + " has been updated" , HttpStatus.OK);
    }

    @GetMapping("/delete/{name}")
    public ResponseEntity<String> deleteAttraction(@PathVariable String name) {
        if (touristService.getTouristAttractionByName(name) == null) {
            return new ResponseEntity<>(name + " could not be found" , HttpStatus.NOT_FOUND);
        }
        touristService.deleteTouristAttraction(name);
        touristService.getAllTouristAttractions().forEach(System.out::println);
        return new ResponseEntity<>(name + " has been deleted" , HttpStatus.OK);
    }
}
