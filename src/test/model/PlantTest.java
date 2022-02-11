package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {
    private Plant testPlant1;
    private Plant testPlant2;

    @BeforeEach
    void setUp() {
        testPlant1 = new Plant("Goldie", "Golden Pothos", "Growing", 5, false);
        testPlant2 = new Plant("Vinny", "English Ivy", "Maintaining", 7, true);
    }

    @Test
    void testConstructor() {
        assertEquals("Goldie", testPlant1.getPlantName());
        assertEquals("Golden Pothos", testPlant1.getPlantType());
        assertEquals("Growing", testPlant1.getPlantStatus());
        assertEquals(5, testPlant1.getPlantWater());
        assertFalse(testPlant1.getWatered());

        assertEquals("Vinny", testPlant2.getPlantName());
        assertEquals("English Ivy", testPlant2.getPlantType());
        assertEquals("Maintaining", testPlant2.getPlantStatus());
        assertEquals(7, testPlant2.getPlantWater());
        assertTrue(testPlant2.getWatered());
    }



    @Test
    void testChangePlantName() {
        testPlant2.setName("Andy");
        assertEquals("Andy", testPlant2.getPlantName());
        testPlant2.setName("Vinny");
        assertEquals("Vinny", testPlant2.getPlantName());
    }

    @Test
    void testChangePlantStatus() {
        assertEquals("Growing", testPlant1.getPlantStatus());
        testPlant1.setStatus("Maintaining");
        assertEquals("Maintaining", testPlant1.getPlantStatus());
        testPlant1.setStatus("Dying");
        assertEquals("Dying", testPlant1.getPlantStatus());
        testPlant1.setStatus("Growing");
        assertEquals("Growing", testPlant1.getPlantStatus());
    }

    @Test
    void testCheckIfPlantIsWatered() {
        assertFalse(testPlant1.getWatered());
        assertTrue(testPlant2.getWatered());
    }

    @Test
    void testWaterPlant() {
        testPlant1.setWatered(true);
        assertTrue(testPlant1.getWatered());
        testPlant2.setWatered(false);
        assertFalse(testPlant2.getWatered());
    }


    @Test
    void testToString() {
        assertTrue(testPlant1.toString().contains("Plant Name: Goldie"
                                            + "\n Plant Type: Golden Pothos"
                                            + "\n Plant Status: Growing"
                                            + "\n Plant Water: 5"));
    }
}