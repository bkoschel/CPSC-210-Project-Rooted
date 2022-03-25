package persistence;

import model.Plant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests PlantJsonTest

// inspired by JsonTest class from JsonSerializationDemo provided by CPSC 210
// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class PlantsJsonTest {
    protected  void checkPlants(String name, String type, String status, int water, boolean watered, Plant plant) {
        assertEquals(name, plant.getPlantName());
        assertEquals(type, plant.getPlantType());
        assertEquals(status, plant.getPlantStatus());
        assertEquals(water, plant.getPlantWater());
        assertEquals(watered, plant.getWatered());
    }
}
