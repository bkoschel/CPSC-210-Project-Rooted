package persistence;

import org.json.JSONObject;

// Effects: returns this item as JSON object
public interface Writable {
    JSONObject toJson();
}
