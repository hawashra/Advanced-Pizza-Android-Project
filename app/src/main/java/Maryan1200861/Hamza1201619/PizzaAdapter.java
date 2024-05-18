package Maryan1200861.Hamza1201619;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    // Add your list of pizzas here

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    //TODO: implement onBindViewHolder
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        // Bind your pizza data to the views here
    }

    @Override
    //TODO: implement getItemCount
    public int getItemCount() {
        // Return the size of your pizza list here
        return 0;
    }

    static class PizzaViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPizza;
        TextView textViewPizzaName;
        Button buttonViewDetails;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPizza = itemView.findViewById(R.id.imageViewPizza);
            textViewPizzaName = itemView.findViewById(R.id.textViewPizzaName);
            buttonViewDetails = itemView.findViewById(R.id.buttonViewDetails);

            buttonViewDetails.setOnClickListener(v -> {
                // Handle button click here
            });
        }
    }
}