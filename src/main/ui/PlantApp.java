package ui;

import model.Plant;
import model.Plants;

import java.util.Scanner;

// Plant organizer application
public class PlantApp {
    private Plant name;
    private Plant type;
    private Plant status;
    private Plant water;
    private Plants myPlants;
    private Scanner input;

    private PlantApp() {
        runPlantApp();
    }

    private void runPlantApp() {
        boolean keepGoing = true;
        String command = null;


    }

    // EFFECTS: displays menu of options to user
    private void displayHomeMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> add plant");
        System.out.println("\t2 -> view collection");
        System.out.println("\t3 -> view plant");
        System.out.println("\t4 -> remove plant");
        System.out.println("\t5 -> quit");
    }

}
