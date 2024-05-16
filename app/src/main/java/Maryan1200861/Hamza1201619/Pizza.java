package Maryan1200861.Hamza1201619;

public class Pizza {

    private int id; // To uniquely identify each pizza
    private String name; // Name of the pizza type

    // Constructor to initialize Pizza objects
    public Pizza(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

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
                "\nID= " + id +
                "\nName= " + name +
                "\n}";
    }
}
