package Maryan1200861.Hamza1201619;

import java.io.Serializable;

public class Pizza implements Serializable {

    private int id; // To uniquely identify each pizza
    private String name; // Name of the pizza type

    private boolean favorite; // To keep track of the favorite status of the pizza

    // Constructor to initialize Pizza objects
    public Pizza(String name) {
        this.name = name;
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
