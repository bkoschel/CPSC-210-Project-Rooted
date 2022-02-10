package model;

import java.util.ArrayList;

public class Plants {
    private ArrayList<Plant> myPlantCollection;
    private Plant testPlant;

    public Plants() {
        myPlantCollection = new ArrayList<>();
    }

    public void addPlant(Plant plant) {
        myPlantCollection.add(plant);
    }

    public boolean containsPlant(Plant plant) {
        return myPlantCollection.contains(plant);
    }

    public void removePlant(Plant plant) {
        if (containsPlant(plant)) {
            myPlantCollection.remove(plant);
        }
    }

    public int getNumberOfPlantsInCollection() {
        return myPlantCollection.size();
    }

    public String getListOfPlantNames() {
        String plants = "";
        for (int i = 0; i < getNumberOfPlantsInCollection(); i++) {
            plants += "Plant Number " + (i + 1) + ": " + myPlantCollection.get(i).getPlantName()
                    + "\n";
        }
        return plants; // stub
    }

    public Plant getPlant(int i) throws IndexOutOfBoundsException {
        return testPlant = myPlantCollection.get(i);

    }

}
