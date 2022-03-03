package persistence;

import model.Plant;
import model.Plants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests PlantJsonReader

// tests were inspired by JsonReaderTest class from JsonSerializationDemo provided by CPSC 210
// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class PlantsJsonReaderTest extends PlantsJsonTest {

    @Test
    void testJsonReaderFileDoesNotExist() {
        PlantJsonReader reader = new PlantJsonReader("./data/doesNotExist.json");
        try {
            Plants plants = reader.read();
            fail("IOException");
        } catch (IOException i) {
            // do something
        }
    }

    @Test
    void testReadEmptyPlantList() {
        PlantJsonReader reader = new PlantJsonReader("./data/testReadEmptyPlantList");
        try {
            Plants plants = reader.read();
            assertEquals(0, plants.getNumberOfPlantsInCollection());
        } catch (IOException i) {
            fail("Can't Read File");
        }
    }

    @Test
    void testReadPlantList() {
        PlantJsonReader reader = new PlantJsonReader("./data/testReadPlantList");
        try {
            Plants plants = reader.read();
            List<Plant> plantList = plants.getPlants();
            assertEquals(2, plantList.size());
            checkPlants("Greg", "fern", "ok", 5, true, plantList.get(0));
            checkPlants("Sam", "cactus", "healthy", 2, false, plantList.get(1));
        } catch (IOException i) {
            fail("Can't read file");
        }

    }

}
