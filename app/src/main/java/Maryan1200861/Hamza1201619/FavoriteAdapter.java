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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private final List<Pizza> favorites;
    private final FavoriteFragment favoriteFragment;

    public FavoriteAdapter(List<Pizza> favorites, FavoriteFragment favoriteFragment) {
        this.favorites = favorites;
        this.favoriteFragment = favoriteFragment;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);

        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Pizza favorite = favorites.get(position);
        holder.textViewPizzaName.setText(favorite.getName());
        holder.imageViewPizza.setImageResource(R.drawable.pizza_image);

        holder.buttonFavorite.setImageResource(R.drawable.fav_filled_icon);


        holder.buttonFavorite.setOnClickListener(v -> {
            UserManager.getInstance().toggleFavorite(favorite);
            favoriteFragment.refreshFavorites();
            Toast.makeText(v.getContext(), "Removed " + favorite.getName() + " from favorites", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPizza;
        TextView textViewPizzaName;
        Button buttonOrder;
        ImageView buttonFavorite;

        public RecyclerView favoriteRecyclerView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPizza = itemView.findViewById(R.id.imageViewPizza);
            textViewPizzaName = itemView.findViewById(R.id.textViewPizzaName);
            buttonOrder = itemView.findViewById(R.id.buttonOrder);
            buttonFavorite = itemView.findViewById(R.id.buttonFavorite);

        }

    }
    public void updateData(ArrayList<Pizza> favorites) {
        this.favorites.clear();
        this.favorites.addAll(favorites);
        notifyDataSetChanged();
    }
}
