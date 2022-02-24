package model;

import java.util.ArrayList;

// Plants is the representation for a  list of plants
// The methods in this class are operations that can be performed on a list of Plants
public class Plants {
    private final ArrayList<Plant> myPlantCollection;

    // EFFECTS: Creates a new ArrayList for Plants to be stored in
    public Plants() {
        myPlantCollection = new ArrayList<>();
    }

    public boolean containsPlant(Plant plant) {
        return myPlantCollection.contains(plant);
    }

    // MODIFIES: this
    public void addPlant(Plant plant) {
        myPlantCollection.add(plant);
    }

    // REQUIRES: there must be at least one item in the list of Plants
    // MODIFIES: this
    public void removePlant(int positionInList) {
        myPlantCollection.remove(positionInList);
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

}
