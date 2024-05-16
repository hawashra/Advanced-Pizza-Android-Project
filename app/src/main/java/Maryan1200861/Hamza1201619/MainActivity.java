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
