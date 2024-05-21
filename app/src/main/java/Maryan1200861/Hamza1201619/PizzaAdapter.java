package Maryan1200861.Hamza1201619;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    // Add your list of pizzas here

    //FIXME: may not be final if the admin can add pizzas
    private final ArrayList<Pizza> pizzas;

    public PizzaAdapter(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    //TODO: implement onBindViewHolder
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {

        Pizza pizza = pizzas.get(position);
        holder.textViewPizzaName.setText(pizza.getName());
        holder.imageViewPizza.setImageResource(R.drawable.pizza_image);

        // Bind your pizza data to the views here
        holder.buttonOrder.setOnClickListener(v -> {
            // Handle button click here

            // Show the PizzaDetailsBottomSheetFragment
            PizzaDetailsBottomSheetFragment.newInstance(pizza)
                    .show(((FragmentActivity) v.getContext()).getSupportFragmentManager(), "pizzaDetailsBottomSheet");


            Toast.makeText(v.getContext(),
                    "You clicked on " + pizza.getName(), Toast.LENGTH_SHORT).show();

        });

        holder.buttonFavorite.setOnClickListener(v -> {
            // Handle favorite button click here

            // Toggle the favorite status of the pizza
            boolean isFavorite = UserManager.getInstance().toggleFavorite(pizza.getName());

            // Set the favorite icon based on the new status
            holder.buttonFavorite.setImageResource(isFavorite ? R.drawable.fav_filled_icon :
                    R.drawable.fav_outline_icon);

        });

    }

    @Override
    //TODO: implement getItemCount
    public int getItemCount() {
        // Return the size of your pizza list here
        return pizzas.size();
    }

    static class PizzaViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPizza;
        TextView textViewPizzaName;
        Button buttonOrder;
        ImageView buttonFavorite;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPizza = itemView.findViewById(R.id.imageViewPizza);
            textViewPizzaName = itemView.findViewById(R.id.textViewPizzaName);
            buttonOrder = itemView.findViewById(R.id.buttonOrder);
            buttonFavorite = itemView.findViewById(R.id.buttonFavorite);
        }
    }
}