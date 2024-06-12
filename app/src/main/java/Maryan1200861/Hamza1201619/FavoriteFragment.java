package Maryan1200861.Hamza1201619;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewFavorite);
        List<Pizza> favorites = UserManager.getInstance().getFavoritePizzas();
        favoriteAdapter = new FavoriteAdapter(favorites, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoriteAdapter);
        return view;
    }

    public void removeFavorite(Pizza pizza) {
        favoriteAdapter.removePizza(pizza);
        // remove the pizza from the database
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
            databaseHelper.removeFavorite(pizza, UserManager.getInstance().getCurrentUser().getEmail());
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error removing favorite", Toast.LENGTH_SHORT).show();
        }
    }
}
