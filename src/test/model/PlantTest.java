package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {
    private Plant testPlant;

    @BeforeEach
    void setUp() {
        testPlant = new Plant("Goldie", "Golden Pothos", "Growing", 5);
    }

    @Test
    void testConstructor() {
        assertEquals("Goldie", testPlant.getPlantName());
        assertEquals("Golden Pothos", testPlant.getPlantType());
        assertEquals("Growing", testPlant.getPlantStatus());
        assertEquals(5, testPlant.getPlantWater());
    }

    @Test
    void testConstructorNoPlantType() {
        testPlant = new Plant("Manu-o-ku", "", "Growing", 1);
        assertEquals("Manu-o-ku", testPlant.getPlantName());
        assertEquals("Unknown Type", testPlant.getPlantType());
        assertEquals("Growing", testPlant.getPlantStatus());
        assertEquals(1, testPlant.getPlantWater());
    }

    @Test
    void testConstructorNoWaterData() {

    }

    @Test
    void testChangePlantName() {
        testPlant = new Plant("Little", "Cactus", "Growing", 1);
        assertEquals("Little", testPlant.getPlantName());
        testPlant.changeName("Biggie");
        assertEquals("Biggie", testPlant.getPlantName());
    }

    @Test
    void testChangePlantStatus() {
        testPlant = new Plant("Little", "Cactus", "Growing", 1);
        assertEquals("Growing", testPlant.getPlantStatus());
        testPlant.changeStatus("Maintaining");
        assertEquals("Maintaining", testPlant.getPlantStatus());
        testPlant.changeStatus("Dying");
        assertEquals("Dying", testPlant.getPlantStatus());
        testPlant.changeStatus("Growing");
        assertEquals("Growing", testPlant.getPlantStatus());
    }

    @Test
    void testNoChangeInWaterFrequency() {
        testPlant = new Plant("Little", "Cactus", "Growing", 1);
        assertEquals(1, testPlant.getPlantWater());
    }

    @Test
    void testIncreaseInWaterFrequency() {

    }

    @Test
    void testDecreaseInWaterFrequency() {

    }


    @Test
    void testToString() {
        assertTrue(testPlant.toString().contains("Plant Name: Goldie"
                                            + "\n Plant Type: Golden Pothos"
                                            + "\n Plant Status: Growing"
                                            + "\n Plant Water: 5"));
    }
}