package Maryan1200861.Hamza1201619;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class PizzasAdapter extends RecyclerView.Adapter<PizzasAdapter.PizzaViewHolder> {
    private ArrayList<Map.Entry<Pizza, Integer>> pizzas;

    public PizzasAdapter(ArrayList<Map.Entry<Pizza, Integer>> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_item, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PizzaViewHolder holder, int position) {
        Map.Entry<Pizza, Integer> entry = pizzas.get(position);

        holder.pizzaNameTextView.setText(entry.getKey().getName());
        holder.pizzaQuantityTextView.setText(String.valueOf(entry.getValue()));
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        public TextView pizzaNameTextView;
        public TextView pizzaQuantityTextView;

        public PizzaViewHolder(View itemView) {
            super(itemView);

            pizzaNameTextView = itemView.findViewById(R.id.pizzaNameTextView);
            pizzaQuantityTextView = itemView.findViewById(R.id.pizzaQuantityTextView);
        }
    }
}