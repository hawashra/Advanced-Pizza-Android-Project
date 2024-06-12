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