package Maryan1200861.Hamza1201619;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class AddAdminFragment extends Fragment {

        private EditText editTextEmail, editTextPhone, editTextFirstName, editTextLastName, editTextPassword, editTextConfirmPassword;
        private Spinner spinnerGender;

        public static String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}";
        public static String phoneRegex = "05\\d{8}";
        public static String passwordRegex = "((?=.*\\d)(?=.*[a-zA-Z]).{8,})";

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_add_admin, container, false);

            editTextEmail = view.findViewById(R.id.editTextEmail);
            editTextPhone = view.findViewById(R.id.editTextPhone);
            editTextFirstName = view.findViewById(R.id.editTextFirstName);
            editTextLastName = view.findViewById(R.id.editTextLastName);
            editTextPassword = view.findViewById(R.id.editTextNewPassword);
            editTextConfirmPassword = view.findViewById(R.id.editTextConfirmPassword);
            spinnerGender = view.findViewById(R.id.spinnerGender);
            Button buttonCreateAccount = view.findViewById(R.id.buttonCreateAccount);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.gender_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerGender.setAdapter(adapter);

            buttonCreateAccount.setOnClickListener(v -> {
                try (DatabaseHelper databaseHelper = new DatabaseHelper(getContext())) {
                    if (databaseHelper.userExists(editTextEmail.getText().toString().trim(), editTextPhone.getText().toString().trim())) {
                        Toast.makeText(getContext(), "Email or phone number already used", Toast.LENGTH_SHORT).show();
                    } else if (validateInputs()) {
                        String hashedPassword = HashPassword.hashPassword(editTextPassword.getText().toString(), getContext());

                        User user1 = new User();
                        user1.setEmail(editTextEmail.getText().toString());
                        user1.setFirstName(editTextFirstName.getText().toString());
                        user1.setLastName(editTextLastName.getText().toString());
                        user1.setPhone(editTextPhone.getText().toString());
                        user1.setGender(spinnerGender.getSelectedItem().toString());
                        user1.setHashedPassword(hashedPassword);

                        registerUser(user1);

                        Intent signInIntent = new Intent(getActivity(), LoginActivity.class);
                        signInIntent.putExtra("email", editTextEmail.getText().toString());
                        startActivity(signInIntent);

                        // NOTE: FOR DEBUGGING PURPOSES ONLY
                        ArrayList<User> allUsers = databaseHelper.getAllUsers();
                        for (User user : allUsers) {
                            Log.d("User", user.getEmail());
                        }
                    }
                } catch (Exception e) {
                    Log.d("db_error", Objects.requireNonNull(e.getMessage()));
                }
            });

            return view;
        }

        public boolean validateInputs() {
            if (!Pattern.matches(emailRegex, editTextEmail.getText().toString().trim())) {
                showToast("Invalid email format.");
                return false;
            }
            if (!Pattern.matches(phoneRegex, editTextPhone.getText().toString().trim())) {
                showToast("Phone number must start with '05' and be 10 digits long.");
                return false;
            }
            if (editTextFirstName.getText().toString().trim().length() < 3) {
                showToast("First name must be at least 3 characters long.");
                return false;
            }
            if (editTextLastName.getText().toString().trim().length() < 3) {
                showToast("Last name must be at least 3 characters long.");
                return false;
            }
            if (!Pattern.matches(passwordRegex, editTextPassword.getText().toString())) {
                showToast("Password must be at least 8 characters long and include at least one letter and one number.");
                return false;
            }
            if (!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())) {
                showToast("Passwords do not match.");
                return false;
            }
            return true;
        }

        private void showToast(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }

        private void registerUser(User user) {
            try (DatabaseHelper databaseHelper = new DatabaseHelper(getContext())) {
                databaseHelper.registerNewUser(user);
            } catch (Exception e) {
                Log.d("db_helper", Objects.requireNonNull(e.getMessage()));
            }
        }
    }
