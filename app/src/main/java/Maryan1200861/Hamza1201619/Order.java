package Maryan1200861.Hamza1201619;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private final int orderId;
    private final String userEmail;
    private final String orderDate;
    private final double totalPrice;
    private final HashMap<Pizza, Integer> pizzas;

    public Order(int orderId, String userEmail, String orderDate, double totalPrice) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.pizzas = new HashMap<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public HashMap<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza, int quantity) {
        this.pizzas.put(pizza, quantity);
    }

    @Override
    @NonNull
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userEmail='" + userEmail + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", totalPrice=" + totalPrice +
                ", pizzas=" + pizzas +
                '}';
    }
}
