package persistence;

import model.Plant;
import model.Plants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Reads JSON data from a given file through a reader

// inspired by JsonReader.java class from JsonSerializationDemo provided by CPSC 210
// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class PlantJsonReader {
    private String file;

    //EFFECTS: constructs a reader and reads from a source file
    // inspired by JsonReader constructor from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public PlantJsonReader(String source) {
        this.file = source;
    }

    // EFFECTS: reads and returns a plant list from a given file;
    // IOException is thrown if while reading the data from the file an error occurs
    // inspired by read() method from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public Plants read() throws IOException {
        String jsonData = readFile(file);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlants(jsonObject);
    }

    // EFFECTS: reads and returns source file as a string
    // inspired by readFile() method from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private String readFile(String file) throws IOException {
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
            stream.forEach(s -> content.append(s));
        }

        return content.toString();
    }

    // EFFECTS: parses Plants from the given JSON object and returns the list of plants
    // inspired by parseWorkRoom() method from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private Plants parsePlants(JSONObject jsonObject) {
        Plants plants = new Plants();
        addPlants(plants, jsonObject);
        return plants;
    }

    // EFFECTS: parses the list of plants from JSON object and adds them to Plants
    // MODIFIES: plants
    // inspired by addThingies() method from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void addPlants(Plants plants, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("plants");
        for (Object json : jsonArray) {
            JSONObject nextPlants = (JSONObject) json;
            addPlant(plants, nextPlants);
        }
    }

    // EFFECTS: parses Plant from JSON object and adds the plant to the list of plants (Plants)
    // MODIFIES: plants
    // inspired by addThingy() method from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void addPlant(Plants plants, JSONObject nextPlants) {
        String name = nextPlants.getString("plantName");
        String type = nextPlants.getString("plantType");
        String status = nextPlants.getString("plantStatus");
        int water = nextPlants.getInt("waterPerWeek");
        boolean watered = nextPlants.getBoolean("watered");
        Plant plant = new Plant(name, type, status, water, watered);
        plants.addPlant(plant);
    }
}
