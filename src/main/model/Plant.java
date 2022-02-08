package model;

import java.util.Objects;

// Represents a plant with a name, type of plant, plant status and watering schedule
public class Plant {
    private String plantName;         // plant name
    private final String plantType;   // the type of plant presented as its common name
    private String plantStatus;       // if the plant is growing, dying or dead
    private int plantWater;           // how many times the plant must be watered per week
    private int plantNumber;          // the plants unique number

    // REQUIRES: name has a non-zero length and is unique from all other plants
    //           type has a non-zero length
    //           status must be one of: growing, maintaining, dying, dead
    //           water must be > 0
    // EFFECTS: plantName is set to name; plantType is set to type; plantStatus is
    //          set to status; plantWater is set to water
    public Plant(String name, String type, String status, int water) {
        plantName = name;
        if (type.length() > 0) {
            plantType = type;
        } else {
            plantType = "Unknown Type";
        }
        plantStatus = status;
        plantWater = water;

    }

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
        return plantWater;
    }



    // REQUIRES: newName must have a non-zero length
    // MODIFIES: this
    // EFFECTS: name of the plant is set to the newName
    public String changeName(String newName) {
        plantName = newName;
        return plantName;
    }

    // REQUIRES: newName must have a non-zero length
    // MODIFIES: this
    // EFFECTS: name of the plant is set to the newName
    public String changeStatus(String newStatus) {
        plantStatus = newStatus;
        return plantStatus;
    }

    public int changeWateringFrequency() {
        if (Objects.equals(plantStatus, "Growing")) {
            return plantWater;
        } else {
            return plantWater += 1;

        }

    }

    public String toString() {
        String waterStr = Integer.toString(plantWater);
        return "Plant Name: " + plantName
                + "\n Plant Type: " + plantType
                + "\n Plant Status: " + plantStatus
                + "\n Plant Water: " + waterStr;
    }

}
