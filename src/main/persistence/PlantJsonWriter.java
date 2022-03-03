package persistence;

import model.Plants;
import org.json.JSONObject;

import java.io.*;

// Writes a JSON representation of the plant list to a given file using a writer

// inspired by JsonWriter.java class from JsonSerializationDemo provided by CPSC 210
// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class PlantJsonWriter {
    private static final int INDENT = 4;
    private PrintWriter writer;
    private String file;

    // EFFECTS: constructs a writer that will write to a given file
    // inspired by JsonWriter from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public PlantJsonWriter(String file) {
        this.file = file;
    }

    // EFFECTS: open's a writer; if the file cannot be opened for writing it
    //         throws a FileNotFoundException
    // MODIFIES: this
    // inspired by open() method from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(file);
    }

    // EFFECTS: writes a JSON representation of plant list to a given file
    // MODIFIES: this
    // inspired by write() method from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void write(Plants plants) {
        JSONObject json = plants.toJson();
        saveToJsonFile(json.toString(INDENT));
    }

    // EFFECTS: closes the writer
    // MODIFIES: this
    // inspired by close() method from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void close() {
        writer.close();
    }

    // EFFECTS: writes a string to the JSON file
    // MODIFIES: this
    // inspired by saveToFile() from JsonSerializationDemo provided by CPSC 210
    // GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void saveToJsonFile(String json) {
        writer.print(json);
    }

}
