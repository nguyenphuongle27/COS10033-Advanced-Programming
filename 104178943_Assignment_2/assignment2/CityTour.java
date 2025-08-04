package assignment2;

import java.util.Map;

// Inherit ATour class
public class CityTour extends ATour{
    private String duration; //Describe the tour duration

	//Get and set methods
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Constructor
    public CityTour(int id, String name, String duration, Map<String, Integer> price) {
        super(id, name, price);
        this.duration = duration;
    }

    @Override
    public String pickupPlace() {
        // TODO Auto-generated method stub
        return null;
    }
}
