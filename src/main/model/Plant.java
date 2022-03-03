package model;

import org.json.JSONObject;
import persistence.CanWrite;

// Represents a plant with a name, type of plant, plant status, how often a plant must be watered per week and
// if the plant has been watered (true) or not (false)
// Some code was inspired by JsonSerializationDemo provided by CPSC 210
//// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class Plant implements CanWrite {
    private String plantName;         // plant name
    private final String plantType;   // the type of plant presented as its common name
    private String plantStatus;       // if the plant is growing, dying or dead
    private final int waterPerWeek;   // how many times the plant must be watered per week
    private boolean watered;          // true if the plant has been watered, false if it hasn't been watered

    // Effects: Creates a plant with a name, the type of plant, if the plant is healthy or not,
    // how much water it needs per week,
    // and if the plant has been watered
    public Plant(String plantName, String plantType, String plantStatus, int waterPerWeek, boolean watered) {
        this.plantName = plantName;
        this.plantType = plantType;
        this.plantStatus = plantStatus;
        this.waterPerWeek = waterPerWeek;
        this.watered = watered;

    }

    // getters
    public String getPlantName() {
        return plantName;
    }

    public String getPlantType() {
        return plantType;
    }

    public String getPlantStatus() {
        return plantStatus;
    }

    public int getPlantWater() {
        return waterPerWeek;
    }

    public boolean getWatered() {
        return watered;
    }

    // setters
    public void setName(String plantName) {
        this.plantName = plantName;
    }

    public void setStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }

    public void setWatered(boolean watered) {
        this.watered = watered;
    }

    // EFFECTS: returns a string representation of a plant
    public String toString() {
        String waterStr = Integer.toString(waterPerWeek);
        String wateredStr = Boolean.toString(watered);
        return "Plant Name: " + plantName
                + "\n Plant Type: " + plantType
                + "\n Plant Status: " + plantStatus
                + "\n Plant Water: " + waterStr
                + "\n Has plant been watered today: " + wateredStr;
    }


    // EFFECTS: returns a JSON object of the plant name, type, status, amount watered per week
    //          and if the plant has been watered or not
    // toJson was from the JsonSerializationDemo provided by CPSC 210
    //// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("plantName", plantName);
        json.put("plantType", plantType);
        json.put("plantStatus", plantStatus);
        json.put("waterPerWeek", waterPerWeek);
        json.put("watered", watered);
        return json;
    }
}
