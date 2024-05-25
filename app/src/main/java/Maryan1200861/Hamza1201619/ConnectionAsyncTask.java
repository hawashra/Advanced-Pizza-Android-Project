package Maryan1200861.Hamza1201619;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.List;
import java.lang.ref.WeakReference;

public class ConnectionAsyncTask extends AsyncTask<String, Void, String> {
    private final WeakReference<Activity> activity;

    public ConnectionAsyncTask(Activity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        Activity activity = this.activity.get();
        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
            ((MainActivity) activity).setButtonText("Connecting...");
            ((MainActivity) activity).setProgress(true);
        }
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpManager.getData(params[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Activity activity = this.activity.get();
        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {

            ((MainActivity) activity).setProgress(false);

            if (result != null && !result.isEmpty()) {
                List<PizzaFactory> pizzasFactories = PizzaJsonParser.getObjectFromJson(result);
                if (pizzasFactories != null && !pizzasFactories.isEmpty()) {
                    try (DatabaseHelper databaseHelper = new DatabaseHelper(activity)) {
                        for (PizzaFactory pizzaFactory : pizzasFactories) {
                            String pizzaName = pizzaFactory.getPizzaName();
                            // if pizza name is not already in the database, add it
                            if (!databaseHelper.isPizzaInDatabase(pizzaName)) {
                                databaseHelper.insertPizza(pizzaFactory.createPizzaSmall());
                                databaseHelper.insertPizza(pizzaFactory.createPizzaMedium());
                                databaseHelper.insertPizza(pizzaFactory.createPizzaLarge());


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

}
