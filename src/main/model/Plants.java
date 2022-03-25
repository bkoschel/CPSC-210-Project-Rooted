package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.CanWrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Plants is the representation for a  list of plants
// The methods in this class are operations that can be performed on a list of Plants
// Some code used here was inspired by JsonSerializationDemo provided by CPSC 210
// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class Plants implements CanWrite {
    private final List<Plant> myPlantCollection;

    // EFFECTS: Creates a new ArrayList for Plants to be stored in
    public Plants() {
        myPlantCollection = new ArrayList<>();
    }

    // EFFECTS: returns true if a given plant is in a list of plants
    public boolean containsPlant(Plant plant) {
        return myPlantCollection.contains(plant);
    }

    // MODIFIES: this
    public void addPlant(Plant plant) {
        myPlantCollection.add(plant);
    }

    // EFFECTS: returns a list of plants to the Plants Class; the list is unmodifiable
    public List<Plant> getPlants() {
        return Collections.unmodifiableList(myPlantCollection);
    }

    // REQUIRES: there must be at least one item in the list of Plants
    // MODIFIES: this
    public void removePlant(Plant plant) {
        myPlantCollection.remove(plant);

    }

    // EFFECTS: the size of this list is returned
    public int getNumberOfPlantsInCollection() {
        return myPlantCollection.size();
    }


    // EFFECTS: returns a string representation of a list of Plants
    public String getListOfPlantNames() {
        StringBuilder plants = new StringBuilder();
        for (int i = 0; i < getNumberOfPlantsInCollection(); i++) {
            plants.append("Plant Number ").append(i + 1).append(": ").append(
                    myPlantCollection.get(i).getPlantName()).append("\n");
        }
        return plants.toString(); // stub
    }

    // REQUIRES: there must be at least one item in the list
    // EFFECTS: returns the plant at a given index i
    public Plant getPlant(int i)  {
        return myPlantCollection.get(i);
    }


    // EFFECTS: returns a JSON object of a list of plants
    // toJson was inspired by toJson method in the JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("plants", plantsToJson());
        return json;
    }

    //EFFECTS: returns a JSON array of plants in the list of plants
    // plantsToJson was inspired by thingiesToJson in the JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private JSONArray plantsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Plant p : myPlantCollection) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }




}
