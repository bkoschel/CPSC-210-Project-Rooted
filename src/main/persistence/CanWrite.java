package persistence;

import org.json.JSONObject;

// An interface creates a JSON object

// inspired by Writable.java interface from JsonSerializationDemo provided by CPSC 210
// GitHub link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface CanWrite {
    // Effects: returns this item as JSON object
    JSONObject toJson();
}
