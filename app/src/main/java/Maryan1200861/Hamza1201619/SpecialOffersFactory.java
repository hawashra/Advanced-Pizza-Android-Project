package Maryan1200861.Hamza1201619;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpecialOffersFactory {

    public static List<Pizza> normalPizzas = null;

    public static List<SpecialOffer> getSpecialOffers() {
        List<SpecialOffer> specialOffers = new ArrayList<>();

        // add some special offers of two pizzas
        SpecialOffer offer1 = new SpecialOffer(1, "Weekend Delight", "2024-06-01", "2024-06-30", 30);
        offer1.addPizza(normalPizzas.get(0));
        offer1.addPizza(normalPizzas.get(4));

        SpecialOffer offer2 = new SpecialOffer(2, "Summer Combo", "2024-06-01", "2024-06-30", 25);
        offer2.addPizza(normalPizzas.get(1));
        offer2.addPizza(normalPizzas.get(3));

        SpecialOffer offer3 = new SpecialOffer(3, "Family Feast", "2024-06-01", "2024-06-30", 25);
        offer3.addPizza(normalPizzas.get(2));
        offer3.addPizza(normalPizzas.get(5));

        SpecialOffer offer4 = new SpecialOffer(4, "Truffle Treat", "2024-06-01", "2024-06-30", 28);
        offer4.addPizza(normalPizzas.get(6));
        offer4.addPizza(normalPizzas.get(9));

        SpecialOffer offer5 = new SpecialOffer(5, "Veggie Delight", "2024-06-01", "2024-06-30", 25);
        offer5.addPizza(normalPizzas.get(7));
        offer5.addPizza(normalPizzas.get(8));

        specialOffers.add(offer1);
        specialOffers.add(offer2);
        specialOffers.add(offer3);
        specialOffers.add(offer4);
        specialOffers.add(offer5);

        return specialOffers;
    }

    public static void addNormalPizzaToList(Pizza pizza) {

        if (normalPizzas == null) {
            normalPizzas = new ArrayList<>();
        }

        normalPizzas.add(pizza);
    }

}
