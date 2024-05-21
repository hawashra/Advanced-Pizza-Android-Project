package Maryan1200861.Hamza1201619;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pizza_details_bottom_sheet, container, false);
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