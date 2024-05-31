package Maryan1200861.Hamza1201619;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button getStartedButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStartedButton = findViewById(R.id.buttonGetStarted);
        progressBar = findViewById(R.id.progressBar);

        setProgress(false);

        getStartedButton.setOnClickListener(v -> {
            setProgress(true);
            ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
            connectionAsyncTask.execute("https://18fbea62d74a40eab49f72e12163fe6c.api.mockbin.io/");


            /**** try to add these
             *
             * salads
             * https://mocki.io/v1/13c6c44f-c0a8-4c96-9b0b-6504789bc72e
             *
             * drinks
             * https://mocki.io/v1/cc37c64d-d9b7-4a4b-b6d3-9bfbca52f2f0
             *
             * fries
             *https://mocki.io/v1/f834a8be-62ad-4a00-87c9-207ed5737460
             *
             */


            try(DatabaseHelper databaseHelper = new DatabaseHelper(this)) {

                ArrayList<Pizza> allPizzas = databaseHelper.getAllPizzas();

                for (Pizza pizza : allPizzas) {
                    Log.d("Pizza", pizza.getName());
                }

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


    public void setButtonText(String text) {
        getStartedButton.setText(text);
    }


    public void setProgress(boolean progress) {
        progressBar.setVisibility(progress ? View.VISIBLE : View.GONE);
    }
}
