package Maryan1200861.Hamza1201619;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpecialOffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpecialOffersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SpecialOffersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpecialOffersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SpecialOffersFragment newInstance(String param1, String param2) {
        SpecialOffersFragment fragment = new SpecialOffersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_offers, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSpecialOffers);
        // TODO: Set up the RecyclerView with your special offers
        // Create a new DatabaseHelper and get the list of pizzas
        try (DatabaseHelper databaseHelper = new DatabaseHelper(getContext())) {

            ArrayList<Pizza> pizzas = databaseHelper.getAllPizzas();

            // Set up your RecyclerView
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new PizzaAdapter(pizzas));

        } catch (Exception e) {
            Log.d("db-error", Objects.requireNonNull(e.getMessage()));
            Toast.makeText(getContext(), "An error occurred in retrieving pizzas from db",
                    Toast.LENGTH_LONG).show();
        }

        EditText editTextSearch = view.findViewById(R.id.editTextSearchSpecialOffers);
        Spinner spinnerFilterType = view.findViewById(R.id.spinnerFilterTypeSpecialOffers);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.filter_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilterType.setAdapter(adapter);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filterType = spinnerFilterType.getSelectedItem().toString();
                //PizzaMenuFragment.filterPizzaList(filterType, s.toString(), getContext());
            }


            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }
}