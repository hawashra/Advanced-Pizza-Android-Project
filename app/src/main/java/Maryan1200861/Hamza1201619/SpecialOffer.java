package Maryan1200861.Hamza1201619;

import java.util.ArrayList;
import java.util.List;

public class SpecialOffer {
    private final int offerId;
    private final String description;
    private final String startDate;
    private final String endDate;
    private final int price;
    private final List<Pizza> pizzas;

    public SpecialOffer(int offerId, String description, String startDate, String endDate, int price) {
        this.offerId = offerId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.pizzas = new ArrayList<>();
    }

    public int getOfferId() {
        return offerId;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }
}