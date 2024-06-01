package Maryan1200861.Hamza1201619;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SpecialOffersFactory {
    private List<Pizza> pizzaList;
    private Random random;

    public SpecialOffersFactory() {
        pizzaList = new ArrayList<>();
        random = new Random();
    }

    public void addPizza(Pizza pizza) {
        pizzaList.add(pizza);
    }

    public Pizza getRandomPizzaWithOffer() {
        Pizza pizza;
        if (pizzaList.isEmpty()) {
            pizza = createRandomPizza();
        } else {
            int index = random.nextInt(pizzaList.size());
            pizza = pizzaList.get(index);
        }
        double offerPrice = calculateOfferPrice(pizza.getPrice());
        return new PizzaWithOffer(pizza, offerPrice);
    }

    private Pizza createRandomPizza() {
        int price = (int) (10 + (20 - 10) * random.nextDouble());
        String[] descriptions = {"Margherita", "Pepperoni", "Hawaiian", "Veggie"};
        String description = descriptions[random.nextInt(descriptions.length)];
        String[] sizes = {"Small", "Medium", "Large"};
        String size = sizes[random.nextInt(sizes.length)];
        return new Pizza(price, description, size);
    }

    private double calculateOfferPrice(double price) {
        return price - (price * (5 + (15 - 5) * random.nextDouble()) / 100);
    }

    private class PizzaWithOffer extends Pizza {
        private double offerPrice;

        public PizzaWithOffer(Pizza pizza, double offerPrice) {
            super(pizza.getPrice(), pizza.getDescription(), pizza.getSize());
            this.offerPrice = offerPrice;
        }

        public double getOfferPrice() {
            return offerPrice;
        }

        @Override
        public String toString() {
            return super.toString() + ", offerPrice=" + offerPrice;
        }
    }

    public static void main(String[] args) {
        SpecialOffersFactory factory = new SpecialOffersFactory();
        factory.addPizza(new Pizza(13, "Cheese Pizza", "Medium"));
        factory.addPizza(new Pizza(16, "BBQ Chicken Pizza", "Large"));

        Pizza randomPizzaWithOffer = factory.getRandomPizzaWithOffer();
        System.out.println(randomPizzaWithOffer);
    }
}
