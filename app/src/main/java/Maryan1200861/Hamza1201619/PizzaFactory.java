package Maryan1200861.Hamza1201619;

public class PizzaFactory {

    private String pizzaName;

    public PizzaFactory(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public Pizza createPizzaSmall() {
        String category = getPizzaCategory(pizzaName);
        return new Pizza(pizzaName, "Small", 10, pizzaName + " small size", category);
    }

    public Pizza createPizzaMedium() {
        String category = getPizzaCategory(pizzaName);
        return new Pizza(pizzaName, "Medium", 15, pizzaName + " medium size", category);
    }

    public Pizza createPizzaLarge() {
        String category = getPizzaCategory(pizzaName);
        return new Pizza(pizzaName, "Large", 20, pizzaName + " large size", category);
    }


    public static String getPizzaCategory(String pizzaName) {
        switch (pizzaName) {

            case "Margarita":
            case "Neapolitan":
                return ("Classic Italian Pizza");
            case "Hawaiian":
                return("Tropical Pizza");
            case "Pepperoni":
            case "New York Style":
                return ("American Pizza");
            case "Calzone":
            case "Tandoori Chicken Pizza":
            case "BBQ Chicken Pizza":
            case "Buffalo Chicken Pizza":
            case "Pesto Chicken Pizza":
                return ("Specialty Pizza");
            case "Seafood Pizza":
                return ("Seafood Pizza");
            case "Vegetarian Pizza":
                return ("Vegetarian Pizza");
            case "Mushroom Truffle Pizza":
                return ("Gourmet Pizza");

            default:
                return ("Unknown Pizza Type");

        }
    }

}
