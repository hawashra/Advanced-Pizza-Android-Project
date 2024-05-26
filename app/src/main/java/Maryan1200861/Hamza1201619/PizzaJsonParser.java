package Maryan1200861.Hamza1201619;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PizzaJsonParser {
    // Parses a JSON string to create a list of Pizza objects.
    public static List<PizzaFactory> getObjectFromJson(String json) {
        List<PizzaFactory> pizzasFactories = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("types");
            // Loop through each item in the JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                String name = jsonArray.getString(i);
                // Create a new Pizza object with the name and an ID (index + 1)
                PizzaFactory pizzaFactory = new PizzaFactory(name);
                pizzasFactories.add(pizzaFactory);
            }
        } catch (JSONException e) {
            Log.d("PizzaJsonParser", "getObjectFromJson: " + e.getMessage());
            return null;
        }
        return pizzasFactories;
    }
}
