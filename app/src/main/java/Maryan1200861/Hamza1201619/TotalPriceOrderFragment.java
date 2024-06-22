package Maryan1200861.Hamza1201619;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TotalPriceOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TotalPriceOrderFragment extends Fragment {
    private TextView tvTotalIncomeEachType, tvTotalIncomeAllTypes;
    ArrayList<Order> orders;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TotalPriceOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TotalPriceOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TotalPriceOrderFragment newInstance(String param1, String param2) {
        TotalPriceOrderFragment fragment = new TotalPriceOrderFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        tvTotalIncomeEachType = view.findViewById(R.id.tvTotalIncomeEachType);
        tvTotalIncomeAllTypes = view.findViewById(R.id.tvTotalIncomeAllTypes);
        try (DatabaseHelper db = new DatabaseHelper(view.getContext())) {
            // Get all orders from the database
            orders = db.getAllOrders();

            // Create and set an adapter for the orders RecyclerView
            OrdersAdapter ordersAdapter = new OrdersAdapter(orders);
            calculateTotalIncome();

        } catch (Exception e) {
            Log.d("orders_error_db", Objects.requireNonNull(e.getMessage()));
        }

        return view;
    }

    private void calculateTotalIncome() {
        Map<String, Double> incomePerType = new HashMap<>();
        double totalIncome = 0;


        for (Order order : orders) {
            for (Map.Entry<Pizza, Integer> entry : order.getPizzas().entrySet()) {
                Pizza pizza = entry.getKey();
                int quantity = entry.getValue();
                double income = pizza.getPrice() * quantity;
                incomePerType.put(pizza.getCategory(), incomePerType.getOrDefault(pizza.getCategory(), 0.0) + income);
                totalIncome += income;
            }
        }

        StringBuilder totalIncomeEachType = new StringBuilder();
        for (Map.Entry<String, Double> entry : incomePerType.entrySet()) {
            totalIncomeEachType.append(entry.getKey()).append(": $").append(entry.getValue()).append("\n");
        }
        tvTotalIncomeEachType.setText(totalIncomeEachType.toString());
        tvTotalIncomeAllTypes.setText(String.format("Total Income for All Types: $%.2f", totalIncome));
    }
}