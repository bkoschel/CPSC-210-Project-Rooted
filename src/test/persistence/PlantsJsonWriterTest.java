package persistence;

import model.Plant;
import model.Plants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests PlantJsonWriter

// inspired by JsonWriterTest class from JsonSerializationDemo provided by CPSC 210
// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class PlantsJsonWriterTest extends PlantsJsonTest {

    @Test
    void testWriteToInvalidFile() {
        try {
            Plants plants = new Plants();
            PlantJsonWriter writer = new PlantJsonWriter("./data/\0illegalfile.json");
            writer.open();
            fail("IOException");
        } catch (IOException i) {
            // to catch exception
        }
    }

    @Test
    void testWriteToEmptyPlantList() {
        try {
            Plants plants = new Plants();
            PlantJsonWriter writer = new PlantJsonWriter("./data/testWriteEmptyPlantList.json");
            writer.open();
            writer.write(plants);
            writer.close();

            PlantJsonReader reader = new PlantJsonReader("./data/testWriteEmptyPlantList.json");
            plants = reader.read();
            assertEquals(0, plants.getNumberOfPlantsInCollection());
        } catch (IOException i) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterPlantList() {
        try {
            Plants plants = new Plants();
            plants.addPlant(new Plant("Greg", "fern", "ok", 5, false));
            plants.addPlant(new Plant("Adam", "cactus", "healthy", 3,
                    true));
            PlantJsonWriter writer = new PlantJsonWriter("./data/testWritePlantList.json");
            writer.open();
            writer.write(plants);
            writer.close();

            PlantJsonReader reader = new PlantJsonReader("./data/testWritePlantList.json");
            plants = reader.read();
            List<Plant> plantList = plants.getPlants();
            assertEquals(2, plants.getNumberOfPlantsInCollection());
            checkPlants("Greg", "fern", "ok", 5, false, plantList.get(0));
            checkPlants("Adam", "cactus", "healthy", 3, true, plantList.get(1));
        } catch (IOException i) {
            fail("IOException should not have been thrown");
        }
    }
}
