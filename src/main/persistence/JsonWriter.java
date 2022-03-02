package persistence;

import model.Plants;
import org.json.JSONObject;

import java.io.*;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String file;

    public JsonWriter(String file) {
        this.file = file;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(file));
    }


}
