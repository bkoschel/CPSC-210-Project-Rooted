package persistence;

import model.Plant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected  void checkPlants(String name, String type, String status, int water, boolean watered, Plant plant) {
        assertEquals(name, plant.getPlantName());
        assertEquals(type, plant.getPlantType());
        assertEquals(status, plant.getPlantStatus());
        assertEquals(water, plant.getPlantWater());
        assertEquals(watered, plant.getWatered());
    }
}
