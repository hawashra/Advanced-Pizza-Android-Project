package Maryan1200861.Hamza1201619;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.List;

public class ConnectionAsyncTask extends AsyncTask<String, Void, String> {
    private final Activity activity;

    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ((MainActivity) activity).setButtonText("Connecting...");
        super.onPreExecute();
        ((MainActivity) activity).setProgress(true);
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpManager.getData(params[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ((MainActivity) activity).setProgress(false);

        if (result != null && !result.isEmpty()) {
            List<Pizza> pizzas = PizzaJsonParser.getObjectFromJson(result);
            if (pizzas != null && !pizzas.isEmpty()) {
                try(DatabaseHelper databaseHelper = new DatabaseHelper(activity)) {
                    for (Pizza pizza : pizzas) {
                        String pizzaName = pizza.getName();
                        // if pizza name is not already in the database, add it
                        if (!databaseHelper.isPizzaInDatabase(pizzaName)) {
                            databaseHelper.insert(pizzaName);
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(activity, "Failed to save data. Please try again.", Toast.LENGTH_LONG).show();

                }

                activity.startActivity(new Intent(activity, RegistrationActivity.class));
            } else {
                Toast.makeText(activity, "No data found. Please try again.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(activity, "Failed to connect or no data found. Please check your connection and try again.", Toast.LENGTH_LONG).show();
        }

        ((MainActivity) activity).setButtonText("Get Started");
    }

}
