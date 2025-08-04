package assignment2;

import java.io.Serializable;


/* The "Ticket" class contains information about travel tickets, including details such as the number of adults, and children, discounts, total price, and customers. */

public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String tourName;
    private int numberAdult;
    private int numberChild;
    private double discount;
    private double total;
    private String pickUp;
    private String status;

    // Transient make Customer not save in file
    private transient Customer customer;
    private double pAndD;
    private String managerName;

 // A complete constructor
    public Ticket(int id, String tourName, int numberAdult, int numberChild, double discount, double total,
            String pickUp, String status, Customer customer, double pAndD, String managerName) {
        this.id = id;
        this.tourName = tourName;
        this.numberAdult = numberAdult;
        this.numberChild = numberChild;
        this.discount = discount;
        this.total = total;
        this.pickUp = pickUp;
        this.status = status;
        this.customer = customer;
        this.pAndD = pAndD;
        this.managerName = managerName;
    }

	//Get and set methods
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPickUp() {
        return pickUp;
    }

    public void setPickUp(String pickUp) {
        this.pickUp = pickUp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public int getNumberAdult() {
        return numberAdult;
    }

    public void setNumberAdult(int numberAdult) {
        this.numberAdult = numberAdult;
    }

    public int getNumberChild() {
        return numberChild;
    }

    public void setNumberChild(int numberChild) {
        this.numberChild = numberChild;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Constructor 1
    public Ticket(int id, String tourName, int numberAdult, int numberChild, double discount, double total,
            String pickUp) {
        this.id = id;
        this.tourName = tourName;
        this.numberAdult = numberAdult;
        this.numberChild = numberChild;
        this.discount = discount;
        this.total = total;
        this.pickUp = pickUp;
        this.status = "Valid"; //assign value
    }

    // Constructor 2
    // extended two more attributes "status" and "customer"
    public Ticket(int id, String tourName, int numberAdult, int numberChild, double discount, double total,
            String pickUp, String status, Customer customer, double pAndD) {
        this.id = id;
        this.tourName = tourName;
        this.numberAdult = numberAdult;
        this.numberChild = numberChild;
        this.discount = discount;
        this.total = total;
        this.pickUp = pickUp;
        this.status = status;
        this.customer = customer;
        this.pAndD = pAndD;
    }

    @Override
    public String toString() {
        return "Ticket - Id: " + id + ", Tour Name: " + tourName + ", Number Adult: " + numberAdult + ", Number Child:"
                + numberChild + ", Discount: " + discount + ", Total: " + total + ", Pick Up: " + pickUp + ", Status: "
                + status + ".";
    }

	//Get and set methods
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getpAndD() {
        return pAndD;
    }

    public void setpAndD(double pAndD) {
        this.pAndD = pAndD;
    }
}
