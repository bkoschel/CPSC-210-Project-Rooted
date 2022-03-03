package ui;


import java.io.FileNotFoundException;

// Starts new plant app as long as the file exists; if a FileNotFoundException
// is thrown print an error message

// inspired by Main.java class from JsonSerializationDemo provided by CPSC 210
// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class Main {
    public static void main(String[] args) {
        try {
            new PlantApp();
        } catch (FileNotFoundException i) {
            System.out.println("Application was not able to run: file not found");
        }
    }
}
