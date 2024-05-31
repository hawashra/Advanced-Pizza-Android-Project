package Maryan1200861.Hamza1201619;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

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
        Pizza pizza = null;
        if (getArguments() != null) {
            pizza = (Pizza) getArguments().getSerializable(ARG_PIZZA);
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

        // Set an OnClickListener for the "Order" button
        view.findViewById(R.id.buttonOrder).setOnClickListener(v -> {
            // TODO: Handle the "Order" button click
        });

    }


}