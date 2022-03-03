package persistence;

import model.Plant;
import model.Plants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriteToInvalidFile() {
        try {
            Plants plants = new Plants();
            JsonWriter writer = new JsonWriter("./data/\0illegalfile.json");
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
            JsonWriter writer = new JsonWriter("./data/testWriteEmptyPlantList.json");
            writer.open();
            writer.write(plants);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmptyPlantList.json");
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
            JsonWriter writer = new JsonWriter("./data/testWritePlantList.json");
            writer.open();
            writer.write(plants);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWritePlantList.json");
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
