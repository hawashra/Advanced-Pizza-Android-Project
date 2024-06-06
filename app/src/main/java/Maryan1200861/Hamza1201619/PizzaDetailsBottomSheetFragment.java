package Maryan1200861.Hamza1201619;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class PizzaDetailsBottomSheetFragment extends BottomSheetDialogFragment {

    private static final String ARG_PIZZA = "pizza";

    public static PizzaDetailsBottomSheetFragment newInstance(Pizza pizza) {
        PizzaDetailsBottomSheetFragment fragment = new PizzaDetailsBottomSheetFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PIZZA, pizza);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza_details_bottom_sheet, container, false);

        // Get the Pizza object from the arguments
        Pizza pizza;
        if (getArguments() != null) {
            pizza = (Pizza) getArguments().getSerializable(ARG_PIZZA);
        } else {
            pizza = null;
        }

        // Populate the views with the pizza details
        if (pizza != null) {
            TextView textViewPizzaName = view.findViewById(R.id.textViewPizzaName);
            TextView textViewPizzaDescription = view.findViewById(R.id.textViewPizzaDescription);
            TextView textViewPizzaPrice = view.findViewById(R.id.textViewPizzaPrice);

            textViewPizzaName.setText(pizza.getName());
            textViewPizzaDescription.setText(pizza.getDescription());
            textViewPizzaPrice.setText(String.valueOf(pizza.getPrice()));
        }




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the pizza from the arguments
        assert getArguments() != null;
        Pizza pizza = (Pizza) getArguments().getSerializable(ARG_PIZZA);

        // TODO: Display the pizza details in the views
        if (pizza != null) {
            // Get the views
            ImageView imageViewPizza = view.findViewById(R.id.imageViewPizza);
            TextView textViewPizzaName = view.findViewById(R.id.textViewPizzaName);
            TextView textViewPizzaDescription = view.findViewById(R.id.textViewPizzaDescription);
            TextView textViewPizzaPrice = view.findViewById(R.id.textViewPizzaPrice);

            // Set the pizza image, name, description, and price
            //imageViewPizza.setImageResource(pizza.getImageResourceId());
            textViewPizzaName.setText(pizza.getName());
            textViewPizzaDescription.setText(pizza.getDescription());
            textViewPizzaPrice.setText(String.valueOf(pizza.getPrice()));
        }

        // Set an OnClickListener for the "Order" button
        view.findViewById(R.id.buttonOrder).setOnClickListener(v -> {
            // TODO: Handle the "Order" button click
            try (DatabaseHelper db = new DatabaseHelper(v.getContext())) {
                // Insert the order into the database
                String email = UserManager.getInstance().getCurrentUser().getEmail();
                assert pizza != null;
                int pizzaId = pizza.getId();
                int quantity = 1;
                double price = pizza.getPrice();
                db.orderPizza(email, pizzaId, quantity, price*quantity);

            } catch (Exception e) {
                Log.d("order_error_db", Objects.requireNonNull(e.getMessage()));
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            View bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            behavior.setPeekHeight((int) (Resources.getSystem().getDisplayMetrics().heightPixels * 0.8));
        }
    }


}