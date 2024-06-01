package Maryan1200861.Hamza1201619;

import java.io.Serializable;

public class Pizza implements Serializable {

    private int id; // To uniquely identify each pizza
    private String name; // Name of the pizza type

    private String size;

    private int price;

    private String description;

    private String category;

    // Constructor
    public Pizza(String name, String size, int price, String description, String category) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.description = description;
        this.category = category;

    }

    public Pizza() {

    }

    public Pizza(int price, String description, String size) {
        this.price = price;
        this.description = description;
        this.size = size;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for ID

    // Getter for Name
    public String getName() {
        return name;
    }

    // Setter for Name
    public void setName(String name) {
        this.name = name;
    }

    // Overriding the toString() method to format the pizza details
    @Override
    public String toString() {
        return "Pizza{" +
                "\nName= " + name +
                "\n}";
    }
/*
    public String toStringOrder() {
        return "Pizza{" +
                "price=" + price +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pizza pizza = (Pizza) obj;
        return name.equals(pizza.name);
    }

}
