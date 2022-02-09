package ui;

import model.Plant;
import model.Plants;

import java.util.Locale;
import java.util.Scanner;

// Plant organizer application
public class PlantApp {
    private Plant plant;
    private Plant userPlant;
    private Plants myPlants;
    private Scanner input;
    private boolean exit;

    PlantApp() {
        runPlantApp();
    }

    private void runPlantApp() {
        exit = true;
        String command = null;
        input = new Scanner(System.in);
        System.out.println("Welcome to Rooted!");


        init();

        while (exit) {
            displayMenu();
            command = input.next();

            processInput(command);
        }
        System.out.println("Goodbye!");
    }

    private void init() {
        myPlants = new Plants();
    }

    private void processInput(String command) {
        if (command.equals("1")) {
            displayPlants();
        } else if (command.equals("2")) {
            addNewPlant();
        } else if (command.equals("3")) {
            removeYourPlant(userPlant);
        } else if (command.equals("4")) {
            exit = false;
        } else {
            System.out.println("Selection invalid");
        }
    }

    private void removeYourPlant(Plant userPlant) {
    }

    private void addNewPlant() {
    }

    private void displayPlants() {
        System.out.println("You currently have " + myPlants.getNumberOfPlantsInCollection()
                            + " in your collection\n");
        System.out.println(myPlants.getListOfPlantNames());
        nameChange();
    }

    private void nameChange() {
    }

    private void displayMenu() {
        System.out.println("\nSelect an option: ");
        System.out.println("\n\t1 - View house plant collection");
        System.out.println("\n\t2 - Add new plant");
        System.out.println("\n\t3 - Remove a plant from the collection");
        System.out.println("\n\t4 - Exit Rooted");
    }

}
