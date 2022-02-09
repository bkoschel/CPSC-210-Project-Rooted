package model;

import java.util.Objects;

// Represents a plant with a name, type of plant, plant status and watering schedule
public class Plant {
    private String plantName;         // plant name
    private final String plantType;   // the type of plant presented as its common name
    private String plantStatus;       // if the plant is growing, dying or dead
    private final int waterPerWeek;           // how many times the plant must be watered per week


    public Plant(String plantName, String plantType, String plantStatus, int waterPerWeek) {
        this.plantName = plantName;
        this.plantType = plantType;
        this.plantStatus = plantStatus;
        this.waterPerWeek = waterPerWeek;

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
        return waterPerWeek;
    }



    // REQUIRES: newName must have a non-zero length
    // MODIFIES: this
    // EFFECTS: name of the plant is set to the newName
    public void setName(String plantName) {
        this.plantName = plantName;
    }

    // REQUIRES: newName must have a non-zero length
    // MODIFIES: this
    // EFFECTS: name of the plant is set to the newName
    public void setStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }




    public String toString() {
        String waterStr = Integer.toString(waterPerWeek);
        return "Plant Name: " + plantName
                + "\n Plant Type: " + plantType
                + "\n Plant Status: " + plantStatus
                + "\n Plant Water: " + waterStr;
    }

}
