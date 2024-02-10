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
    public ResponseEntity<Void> addAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateAttraction(@RequestParam String name, @RequestBody TouristAttraction updatedAttraction) {
        touristService.updateTouristAttraction(name, updatedAttraction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/{name}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable String name) {
        touristService.deleteTouristAttraction(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
