package com.example.touristguideapi.repository;

import com.example.touristguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private List<TouristAttraction> touristAttractions;

    public TouristRepository() {
        touristAttractions = new ArrayList<>();

        touristAttractions.add(new TouristAttraction("The Demon", "An adrenalin rush that lasts several minutes."));
        touristAttractions.add(new TouristAttraction("The Golden Tower", "Do you like heights, speed and feeling the wind in your hair? Then just try the Golden Tower!"));
        touristAttractions.add(new TouristAttraction("The Star Flyer", "With an impressive height of 80 metres, the Star Flyer can call itself one of the highest carousel in Northern Europe. Dare you let yourself go on it?"));
    }

    // Create
    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
    }

    // Read
    public List<TouristAttraction> getAllTouristAttractions() {
        return touristAttractions;
    }

    public TouristAttraction getTouristAttractionByName(String name) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }

    // Update
    public void updateTouristAttraction(String name, TouristAttraction updatedAttraction) {
        for (int i = 0; i < touristAttractions.size(); i++) {
            if (touristAttractions.get(i).getName().equalsIgnoreCase(name)) {
                touristAttractions.set(i, updatedAttraction);
                return;
            }
        }
    }

    // Delete
    public void deleteTouristAttraction(String name) {
        touristAttractions.removeIf(attraction -> attraction.getName().equalsIgnoreCase(name));
    }
}
