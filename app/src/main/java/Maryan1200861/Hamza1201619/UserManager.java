package Maryan1200861.Hamza1201619;

import java.util.ArrayList;
import android.content.Context;

public class UserManager {
    private static UserManager instance;
    private User currentUser;
    private static ArrayList<Pizza> favoritePizzas;
    private static Context context;

    private UserManager() {

    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
            favoritePizzas = new ArrayList<>();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private void addFavoritePizza(Pizza pizza) {
        favoritePizzas.add(pizza);
    }

    private void removeFavoritePizza(Pizza pizzaName) {
        favoritePizzas.remove(pizzaName);
    }

    public void setFavoritePizzas(ArrayList<Pizza> favoritePizzas) {
        UserManager.favoritePizzas = favoritePizzas;
    }

    public boolean toggleFavorite(Pizza pizza) {
        if (favoritePizzas.contains(pizza)) {
            removeFavoritePizza(pizza);
            return false;
        } else {
            addFavoritePizza(pizza);
            return true;
        }
    }

    public void clearCurrentUser() {
        this.currentUser = null;
    }

    public ArrayList<Pizza> getFavoritePizzas() {
        return favoritePizzas;
    }

    public boolean isFavoritePizza(Pizza pizza) {
        return favoritePizzas.contains(pizza);
    }
}