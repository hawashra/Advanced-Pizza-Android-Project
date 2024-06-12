package Maryan1200861.Hamza1201619;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class CallUsFragment extends Fragment {

        public CallUsFragment() {}

        public static CallUsFragment newInstance() {
            return new CallUsFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_call_us, container, false);

            Button btnCall = view.findViewById(R.id.btnCall);
            Button btnMap = view.findViewById(R.id.btnMap);
            Button btnEmail = view.findViewById(R.id.btnEmail);
            ImageView carImage = view.findViewById(R.id.carImage);

            btnCall.setOnClickListener(v -> {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0599000000"));
                startActivity(callIntent);
            });

            btnMap.setOnClickListener(v -> {
                Uri gmmIntentUri = Uri.parse("geo:31.961013,35.190483?q=31.961013,35.190483(Restaurant)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });

            btnEmail.setOnClickListener(v -> {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","AdvancePizza@Pizza.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, I would like to inquire about...");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            });

            // Load the animation
            Animation carAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.car_animation);

            // Start the animation
            carImage.startAnimation(carAnimation);

            return view;
        }
}