package assignment2;

import java.util.HashMap;
import java.util.Map;

public abstract class ATour {
    private int id;
    private String name;
    // Price for adult and child
    private Map<String, Integer> price = new HashMap<>(); //Use "HashMap" to store adult and child ticket prices
    private int numberAvailability;

	//Get and set methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getPrice() {
        return price;
    }

    public void setPrice(Map<String, Integer> price) {
        this.price = price;
    }

    public int getNumberAvailability() {
        return numberAvailability;
    }

    public void setNumberAvailability(int numberAvailability) {
        this.numberAvailability = numberAvailability;
    }

    // Constructor
    public ATour(int id, String name, Map<String, Integer> price) {
        this.id = id;
        this.name = name;
        this.price = price;
        // Maximum of number availability is 40
        this.numberAvailability = 40;
    }

    // Method calculation discount depend on number adult and child
    public double discountPercent(int numberAdult, int numberChild) {
        // 10% discount from the total ticket price is offered for 1 Adult and 1 Child or 2 Adults and 1 Child
        if((numberAdult == 1 && numberChild == 1) || (numberAdult == 2 && numberChild == 1)) {
            return 0.1;
        }
        // For families with 4 and above 15% discount is offered. 
        else if(numberAdult + numberChild >= 4) {
            return 0.15;
        }

        // Else return 0
        return 0;
    }

    //Calculate discount amount
    public double disCount(int numberAdult, int numberChild) {
        return (price.get("adult") * numberAdult + price.get("child") * numberChild) * this.discountPercent(numberAdult, numberChild);
    }

    //total amount after discount
    public double total(int numberAdult, int numberChild) {
        return price.get("adult") * numberAdult + price.get("child") * numberChild - disCount(numberAdult, numberChild);
    }

    public abstract String pickupPlace();

    public void printToString() {
        System.out.println(id + " : " + name);
    };
}
