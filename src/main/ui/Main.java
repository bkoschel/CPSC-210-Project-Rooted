package ui;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new PlantApp();
        } catch (FileNotFoundException i) {
            System.out.println("Application was not able to run: file not found");
        }
    }
}
