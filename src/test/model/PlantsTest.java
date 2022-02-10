package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlantsTest {
    private Plants testPlants1;
    private Plants testPlants2;
    private Plant testPlant1;
    private Plant testPlant2;

    @BeforeEach
    void setUp() {
        testPlants1 = new Plants();
        testPlants2 = new Plants();
        testPlant1 = new Plant("Goldie", "Golden Pothos", "Growing", 5);
        testPlant2 = new Plant("Vinny", "English Ivy", "Maintaining", 7);
    }

    @Test
    public void testAddPlant() {
        testPlants1.addPlant(testPlant1);
        testPlants2.addPlant(testPlant2);
        assertTrue(testPlants1.containsPlant(testPlant1));
        assertTrue(testPlants2.containsPlant(testPlant2));
        assertFalse(testPlants2.containsPlant(testPlant1));
    }

    @Test
    public void testContainsPlant() {
        assertFalse(testPlants1.containsPlant(testPlant1));
        testPlants1.addPlant(testPlant1);
        assertTrue(testPlants1.containsPlant(testPlant1));
        testPlants2.addPlant(testPlant2);
        assertTrue(testPlants2.containsPlant(testPlant2));
        assertFalse(testPlants2.containsPlant(testPlant1));
    }

    @Test
    public void testRemovePlant() {
        testPlants1.addPlant(testPlant1);
        testPlants1.addPlant(testPlant2);
        assertTrue(testPlants1.containsPlant(testPlant1));
        assertTrue(testPlants1.containsPlant(testPlant2));
        testPlants1.removePlant(testPlant2);
        assertFalse(testPlants1.containsPlant(testPlant2));
        assertTrue(testPlants1.containsPlant(testPlant1));
        testPlants1.removePlant(testPlant1);
        assertFalse(testPlants1.containsPlant(testPlant1));
        testPlants1.removePlant(testPlant1);
        assertFalse(testPlants1.containsPlant(testPlant1));

    }

    @Test
    public void testNumberOfPlantsInPlantCollection() {
        testPlants1.addPlant(testPlant1);
        assertEquals(1, testPlants1.getNumberOfPlantsInCollection());
        testPlants1.addPlant(testPlant2);
        assertEquals(2, testPlants1.getNumberOfPlantsInCollection());
        assertEquals(0, testPlants2.getNumberOfPlantsInCollection());
    }

    @Test
    public void testGetListOfPlantNames() {
        assertEquals("", testPlants1.getListOfPlantNames());
        testPlants1.addPlant(testPlant1);
        assertEquals("Plant Number 1: Goldie\n", testPlants1.getListOfPlantNames());
        testPlants1.addPlant(testPlant2);
        assertEquals("Plant Number 1: Goldie\n"
                           + "Plant Number 2: Vinny\n", testPlants1.getListOfPlantNames());
    }


    @Test
    public void testGetPlant() {
        testPlants1.addPlant(testPlant1);
        testPlants1.addPlant(testPlant2);
        assertEquals(testPlant1, testPlants1.getPlant(0));
        assertEquals(testPlant2, testPlants1.getPlant(1));
    }





}
