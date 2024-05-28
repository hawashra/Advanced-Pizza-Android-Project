package Maryan1200861.Hamza1201619;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private CheckBox checkBoxRememberMe;
    private SharedPreferencesManager prefsManager;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        dbHelper = new DatabaseHelper(this);
        prefsManager = SharedPreferencesManager.getInstance(this);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);

        if (getIntent().getBooleanExtra("clearPassword", false)) {
            editTextPassword.setText("");
        }

        try {
            Intent intent = getIntent();
            String email = intent.getStringExtra("email");
            editTextEmail.setText(email);

        } catch (Exception e) {
            Log.d("no-email", Objects.requireNonNull(e.getMessage()));
        }

        // Load remembered
        if (!prefsManager.getRememberedEmail().isEmpty()) {
            editTextEmail.setText(prefsManager.getRememberedEmail());
            checkBoxRememberMe.setChecked(true);
        }

        findViewById(R.id.buttonSignIn).setOnClickListener(v -> performLogin());
        findViewById(R.id.textViewSignUp).setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignUpActivity.class)));
    }

    private void performLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (authenticate(email, password)) {
            // Save or clear the email based on the Remember Me checkbox
            if (checkBoxRememberMe.isChecked()) {
                prefsManager.setRememberMe(email);
            } else {
                prefsManager.clearRememberMe();
            }

            setUserManagerInstance(email);

            // Set the user's favorite pizzas from the database
            setUserManagerFavoritePizzas();


            // Proceed to special menu activity or dashboard
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUserManagerFavoritePizzas() {
        try(DatabaseHelper databaseHelper = new DatabaseHelper(this)) {
            UserManager.getInstance().setFavoritePizzas(databaseHelper.getFavoritePizzas(UserManager.getInstance().getCurrentUser().getEmail()));
        } catch (Exception e) {
            Log.d("db-error", Objects.requireNonNull(e.getMessage()));
            Toast.makeText(this, "An error occurred in retrieving favorite pizzas from db",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void setUserManagerInstance(String email) {
        try(DatabaseHelper databaseHelper = new DatabaseHelper(this)) {
            User user = databaseHelper.getUserByEmail(email);
            UserManager.getInstance().setCurrentUser(user);
        } catch (Exception e) {
            Log.d("db-error", Objects.requireNonNull(e.getMessage()));
            Toast.makeText(this, "An error occurred in retrieving user from db",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean authenticate(String email, String password) {

        Log.d("pwd", password);
        Log.d("hashed", Objects.requireNonNull(HashPassword.hashPassword(password, this)));
        Log.d("email", email);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USERS + " WHERE "
                + DatabaseHelper.COLUMN_USER_EMAIL + " = ? AND " + DatabaseHelper.COLUMN_USER_HASHED_PASSWORD + " = ?",
                new String[]{email, HashPassword.hashPassword(password, this)});

        boolean authenticated = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return authenticated;
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }


}
