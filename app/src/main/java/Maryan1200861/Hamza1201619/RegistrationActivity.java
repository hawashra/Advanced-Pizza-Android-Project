package Maryan1200861.Hamza1201619;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private Button signInButton;
    private Button signUpButton;
    private TextView signAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize buttons
        signInButton = findViewById(R.id.buttonSignIn);
        signUpButton = findViewById(R.id.buttonSignUp);
        signAdmin = findViewById(R.id.signAdmin);

        signAdmin.setOnClickListener(v -> startActivity(new Intent(RegistrationActivity.this, AdminLoginActivity.class)));


        // Set click listener for the sign in button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginActivity
                Intent signInIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(signInIntent);
            }
        });

        // Set click listener for the sign up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SignUpActivity
                Intent signUpIntent = new Intent(RegistrationActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
    }
}
