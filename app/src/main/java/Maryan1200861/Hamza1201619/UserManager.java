package Maryan1200861.Hamza1201619;

import java.util.ArrayList;

public class UserManager {
    private static UserManager instance;
    private User currentUser;
    private static ArrayList<String> favoritePizzas;

    private UserManager() {}

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

    private void addFavoritePizza(String pizzaName) {
        favoritePizzas.add(pizzaName);
    }

    private void removeFavoritePizza(String pizzaName) {
        favoritePizzas.remove(pizzaName);
    }

    public void setFavoritePizzas(ArrayList<String> favoritePizzas) {
        UserManager.favoritePizzas = favoritePizzas;
    }


    public boolean toggleFavorite(String pizzaName) {
        if (favoritePizzas.contains(pizzaName)) {
            removeFavoritePizza(pizzaName);
            return false;
        } else {
            addFavoritePizza(pizzaName);
            return true;
        }
    }


}
