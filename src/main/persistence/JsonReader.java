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

public class JsonReader {
    private String file;

    public JsonReader(String source) {
        this.file = source;
    }

    public Plants read() throws IOException {
        String jsonData = readFile(file);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlants(jsonObject);
    }

    private String readFile(String file) throws IOException {
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
            stream.forEach(s -> content.append(s));
        }

        return content.toString();
    }

    private Plants parsePlants(JSONObject jsonObject) {
        Plants plants = new Plants();
        addPlants(plants, jsonObject);
        return plants;
    }

    private void addPlants(Plants plants, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("plants");
        for (Object json : jsonArray) {
            JSONObject nextPlants = (JSONObject) json;
            addPlant(plants, nextPlants);
        }
    }

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
