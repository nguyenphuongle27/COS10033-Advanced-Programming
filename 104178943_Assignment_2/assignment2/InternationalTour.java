package assignment2;

import java.util.Map;

public class InternationalTour extends ATour {
    private String inclution; //Store information about services included in the tour
    private int days; //Number of days of tour.

	//Get and set methods
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    // Constructor
    public InternationalTour(int id, String name, String inclution, Map<String, Integer> price, int days) {
        super(id, name, price);
        this.inclution = inclution;
        this.days = days;
    }

    @Override
    public double disCount(int numberAdult, int numberChild) {
        return 0;
    }

    //Get and set methods
    @Override
    public String pickupPlace() {
        return "airport";
    }

    public String getInclution() {
        return inclution;
    }

    public void setInclution(String inclution) {
        this.inclution = inclution;
    }
}
