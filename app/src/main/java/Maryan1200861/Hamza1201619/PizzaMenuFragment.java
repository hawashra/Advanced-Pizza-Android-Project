package Maryan1200861.Hamza1201619;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PizzaMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PizzaMenuFragment extends Fragment {

    private RecyclerView recyclerView;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PizzaMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PizzaMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PizzaMenuFragment newInstance(String param1, String param2) {
        PizzaMenuFragment fragment = new PizzaMenuFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza_menu, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        // Set up your RecyclerView here (LayoutManager, Adapter, etc.)

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

        return view;
    }

}