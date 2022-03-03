package ui;

import model.Plant;
import model.Plants;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// An app to help keep track of your plants and keep them healthy
// PlantApp was inspired by the TellerApp provided in CPSC 210
// GitHub Link: https://github.students.cs.ubc.ca/CPSC210/TellerAppRobust.git
public class PlantApp {
    private static final String JSON_FILE = "./data/plantList.json";
    private Plants myPlants;
    private Scanner input;
    private boolean exit;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Plant App
    PlantApp() throws FileNotFoundException {
        jsonReader = new JsonReader(JSON_FILE);
        jsonWriter = new JsonWriter(JSON_FILE);
        runPlantApp();
    }


    // runPlantApp was inspired by the code from the TellerApp linked above
    // EFFECTS: Welcomes user, processes user input and leaves a message when application
    // is exited
    private void runPlantApp() {
        exit = true;
        String command;
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

    // MODIFIES: this
    // EFFECTS: initializes the list of plants
    // code inspired by TellerApp init() method
    private void init() {
        myPlants = new Plants();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // method inspired by TellerApp processCommand method
    private void processInput(String command) {
        if (command.equals("1")) {
            displayPlants();
        } else if (command.equals("2")) {
            addNewPlant();
        } else if (command.equals("3")) {
            removeYourPlant();
        } else if (command.equals("4")) {
            changePlantStatus();
        } else if (command.equals("5")) {
            waterPlants();
        } else if (command.equals("6")) {
            savePlants();
        } else if (command.equals("7")) {
            loadPlants();
        } else if (command.equals("8")) {
            exit = false;
        } else {
            System.out.println("Selection invalid");
        }
    }

    private void loadPlants() {
        try {
            myPlants = jsonReader.read();
            System.out.println("Loaded Plant List to " + JSON_FILE);
        } catch (IOException i) {
            System.out.println("Unable to read file: " + JSON_FILE);
        }

    }

    private void savePlants() {
        try {
            jsonWriter.open();
            jsonWriter.write(myPlants);
            jsonWriter.close();
            System.out.println("Saved Plant List to " + JSON_FILE);
        } catch (FileNotFoundException i) {
            System.out.println("Cannot write to the file called: " + JSON_FILE);
        }
    }

    // REQUIRES: there must be at least one plant item in the list of plants;
    //           second input must be a Y or N
    // MODIFIES: this
    // EFFECTS: lets the user choose a plant to water based off the plant's position
    //          in the list; if the plant is watered it congratulates the user; if the user has
    //          not watered the plant it asks them if they would like to water the plant;
    //          directs the user to the method to water their plant
    private void waterPlants() {
        String user;
        int number;
        Plant plant;

        System.out.println("Enter the plant number of the plant you wish to water:\n");
        number = input.nextInt();
        number = number - 1;
        plant = myPlants.getPlant(number);

        System.out.println("Have you watered your plant today (Y/N)?\n");
        user = input.next();
        user.toLowerCase();


        if (user.equals("y")) {
            System.out.println("Yay! Your plant thanks you!");
        } else if (user.equals("n")) {
            System.out.println("Would you like to water your plant? (Y/N)\n");
            waterPlant(plant);
        }
    }

    // REQUIRES: there must be at least one plant item in the list of plants
    //           input must be Y or N
    // MODIFIES: this
    // EFFECTS: waters not watered plant selected by the user
    //          if plant is not watered it warns the user
    private void waterPlant(Plant plant) {
        String user;
        user = input.next();
        user.toLowerCase();

        if (user.equals("y")) {
            System.out.println("Yay! Your plant thanks you!");
            plant.setWatered(true);
        } else if (user.equals("n")) {
            System.out.println("Your plant might be struggling\n");
            plant.setWatered(false);
        }

    }

    // REQUIRES: there must be at least one plant item in the list of plants
    // MODIFIES: this
    // EFFECTS: prompts the user to change the status of their plant
    //          used if the plant goes from healthy to dying or vice versa
    private void changePlantStatus() {
        String status;
        int number;
        Plant plant;

        System.out.println("Which plant would you like to change the status of?\n"
                            + "Please enter the number associated with your plant");
        number = input.nextInt();
        number = number - 1;

        System.out.println("Is your plant now growing, staying the same or dying?\n"
                            + "Please input your plants new status");
        status = input.next();

        plant = myPlants.getPlant(number);
        plant.setStatus(status);

    }

    // REQUIRES: there must be at least one plant item in the list of plants
    // MODIFIES: this
    // EFFECTS: prompts the user to choose a plant at a given location in the list
    //          to be removed; removes plant
    private void removeYourPlant() {
        int position;
        System.out.println("Which plant would you like to remove?\n"
                + "Please enter the number associated with the plant you want to remove: ");
        position = input.nextInt();
        myPlants.removePlant(position - 1);

    }

    // REQUIRES: user must input a single word string for the name and type of plant;
    //           the status must be healthy, ok or dead;
    //           water must be an integer less than or equal to 7;
    //           watered must be true or false lower case
    // MODIFIES: this
    // EFFECTS: creates a new plant when user answers the prompt questions;
    //          the new plant is added to the list of plants
    private void addNewPlant() {
        String name;
        String type;
        String status;
        int water;
        boolean watered;

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
        System.out.println("Have you watered yor plant today?\n" + "Enter true or false");
        watered = input.nextBoolean();

        Plant user = createPlant(name, type, status, water, watered);

    }

    // MODIFIES: this
    // EFFECTS: creates a new plant upon initialization of the program and adds the
    //          plant to the list
    private Plant createPlant(String name, String type, String status, int water, boolean watered) {
        Plant plant = new Plant(name, type, status, water, watered);
        myPlants.addPlant(plant);
        return plant;
    }


    // EFFECTS: displays the number of plants in the collection;
    //          if there are zero plants it suggests adding a plant;
    //          otherwise displays a plant
    private void displayPlants() {
        System.out.println("You currently have " + myPlants.getNumberOfPlantsInCollection()
                            + " plants in your collection\n");
        if (myPlants.getNumberOfPlantsInCollection() == 0) {
            System.out.println("Oh no! You have 0 plants in your collection\n"
                                + "Let's add a plant!\n");
            addNewPlant();
        }
        System.out.println(myPlants.getListOfPlantNames());
        viewPlant();
    }

    // EFFECTS: prompts the user if they would like the details of a plant entry
    private void viewPlant() {
        System.out.println("Would you like to view the details of a plant? (Y/N)");
        viewPlantDetailsMenu();
    }

    // REQUIRES: input must be a "Y" or a "N"
    // EFFECTS: asks user if they would like to view their plant otherwise returns
    //          them to the main menu
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

    // REQUIRES: there must be at least one plant item in the list of items
    // EFFECTS: asks the user what plant they would like to view and give the details
    //          of the plant selected
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

    // EFFECTS: displays menu of options to choose from to the user
    // code inspired by TellerApp displayMenuMethod
    private void displayMenu() {
        System.out.println("\nSelect an option: ");
        System.out.println("\t1 - View house plant collection");
        System.out.println("\t2 - Add new plant");
        System.out.println("\t3 - Remove a plant from the collection");
        System.out.println("\t4 - Change Plant Status");
        System.out.println("\t5 - Water Plant");
        System.out.println("\t6 - Save Plant List");
        System.out.println("\t7 - Load Plant List");
        System.out.println("\t8 - Exit Application");
    }

}
