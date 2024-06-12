package Maryan1200861.Hamza1201619;

import static Maryan1200861.Hamza1201619.HashPassword.hashPassword;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AdminProfileFragment extends Fragment {

    private EditText etFirstName, etLastName, etEmail, etPassword, etPhoneNumber,etpasswordConfirm;
    private Button btnSave;
    private Button changeProfilePictureButton;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView ivProfilePicture;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get references to the EditText elements
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etPassword = view.findViewById(R.id.etPassword);
        etpasswordConfirm = view.findViewById(R.id.editTextConfirmNewPassword);
        etEmail = view.findViewById(R.id.etEmail);
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber);
        ivProfilePicture = view.findViewById(R.id.ivProfilePicture);
        btnSave = view.findViewById(R.id.btnSave);

        // Set the EditText values to the user's current information
        User currentUser = UserManager.getInstance().getCurrentUser();
        etFirstName.setText(currentUser.getFirstName());
        etLastName.setText(currentUser.getLastName());
        etPhoneNumber.setText(currentUser.getPhone());
        Bitmap profilePicture;

        try(DatabaseHelper db = new DatabaseHelper(getContext())) {
            profilePicture = db.getUserProfilePicture(currentUser.getEmail());
        } catch (Exception e) {
            profilePicture = null;
        }

        if (profilePicture != null) {
            ivProfilePicture.setImageBitmap(profilePicture);
        }

        changeProfilePictureButton= view.findViewById(R.id.buttonChangeProfilePicture);
        changeProfilePictureButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        });


        // Set an OnClickListener for the save button
        Button saveButton = view.findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(v -> {

            // Get the new values from the EditText elements
            String newFirstName = etFirstName.getText().toString();
            String newLastName = etLastName.getText().toString();
            String newPhoneNumber = etPhoneNumber.getText().toString();
            String newPassword = etPassword.getText().toString();
            String confirmPassword = etpasswordConfirm.getText().toString();

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.matches(SignUpActivity.passwordRegex)) {
                Toast.makeText(getContext(), "Password must be at least 8 characters long and contain at least one letter and one number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPhoneNumber.matches(SignUpActivity.phoneRegex)) {
                Toast.makeText(getContext(), "Invalid phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (newFirstName.isEmpty() || newLastName.isEmpty() || newPhoneNumber.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            currentUser.setFirstName(newFirstName);
            currentUser.setLastName(newLastName);
            currentUser.setPhone(newPhoneNumber);
            currentUser.setHashedPassword(HashPassword.hashPassword(newPassword, getContext()));

            try (DatabaseHelper db = new DatabaseHelper(getContext())) {
                db.updateUserInformation(currentUser);
                db.updateUserProfilePicture(currentUser.getEmail(), ((BitmapDrawable) ivProfilePicture.getDrawable()).getBitmap());
            } catch (Exception e) {
                Toast.makeText(getContext(), "An error occurred while updating your profile", Toast.LENGTH_SHORT).show();
                Log.d("profile-update-error", e.getMessage());
                return;
            }

            // Show a toast message to indicate that the user's information has been updated
            Toast.makeText(getContext(), "Profile updated", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that the result is from the image gallery and is successful
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            // Get the selected image URI
            Uri selectedImageUri = data.getData();

            // Set the ImageView's image to the selected image
            ivProfilePicture.setImageURI(selectedImageUri);
        }
    }
}