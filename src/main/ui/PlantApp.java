package ui;

import model.Plant;
import model.Plants;

import java.util.Scanner;

// Plant organizer application
public class PlantApp {
    private Plant plant;
    private Plant userPlantInput;
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
            removeYourPlant(userPlantInput);
        } else if (command.equals("4")) {
            exit = false;
        } else {
            System.out.println("Selection invalid");
        }
    }

    private void removeYourPlant(Plant userPlant) {
    }

    private void addNewPlant() {
        String name;
        String type;
        String status;
        int water;

        System.out.println("Please enter information about your plant:\n");
        System.out.println("What is the name of your Plant?: ");
        name = input.next();
        System.out.println("What type of plant is it?\n"
                + "For example: cactus, fern or succulent: ");
        type = input.next();
        System.out.println("How healthy is your plant?\n"
                + "Choose one of the three options: healthy, ok or dead: ");
        status = input.next();
        System.out.println("How many times your plant needs to be watered per week\n"
                + "(Cannot be more than 7 times per week): ");
        water = input.nextInt();

        userPlantInput = createPlant(name, type, status, water);
        checkPlantCollection(userPlantInput);
    }



    private void checkPlantCollection(Plant userPlantInput) {
    }

    private Plant createPlant(String name, String type, String status, int water) {
        plant = new Plant(name, type, status, water);
        myPlants.addPlant(plant);
        return plant;
    }

    private void displayPlants() {
        System.out.println("You currently have " + myPlants.getNumberOfPlantsInCollection()
                            + " in your collection\n");
        if (myPlants.getNumberOfPlantsInCollection() == 0) {
            System.out.println("Oh no! You have 0 plants in your collection\n"
                                + "Let's add a plant!\n");
            addNewPlant();
        }
        System.out.println(myPlants.getListOfPlantNames());
        viewPlant();
    }

    private void viewPlant() {
        System.out.println("Would you like to view the details of a plant? (Y/N)");
        viewPlantDetailsMenu();
    }

    private void viewPlantDetailsMenu() {
        String command;
        input = new Scanner(System.in);
        command = input.next();
        command = command.toUpperCase();

        if (command.equals("Y")) {
            viewPlantDetails();
        }
        if (command.equals("N")) {
            System.out.println("Returning to main menu");
        }

    }

    private void viewPlantDetails() {
        int plantNumber;
        Plant plant1;
        input = new Scanner(System.in);

        System.out.println("What plant you would you like to view?\n"
                            + "Please choose from the list of plants above and enter the number for that plant:");
        myPlants.getListOfPlantNames();

        plantNumber = input.nextInt();
        plantNumber = plantNumber - 1;
        plant1 = myPlants.getPlant(plantNumber);
        System.out.println(plant1.toString());

    }


    private void displayMenu() {
        System.out.println("\nSelect an option: ");
        System.out.println("\t1 - View house plant collection");
        System.out.println("\t2 - Add new plant");
        System.out.println("\t3 - Remove a plant from the collection");
        System.out.println("\t4 - Exit Rooted");
    }

}
