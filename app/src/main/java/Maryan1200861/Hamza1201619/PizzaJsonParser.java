package Maryan1200861.Hamza1201619;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PizzaJsonParser {
    // Parses a JSON string to create a list of Pizza objects.
    public static List<Pizza> getObjectFromJson(String json) {
        List<Pizza> pizzas = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("types");
            // Loop through each item in the JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                String name = jsonArray.getString(i);
                // Create a new Pizza object with the name and an ID (index + 1)
                Pizza pizza = new Pizza(i + 1, name);
                pizzas.add(pizza);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return pizzas;
    }
}
