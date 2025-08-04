package assignment2;

import java.util.Map;

// Inherit ATour class
public class AttractionTour extends ATour {
    private String inclution; //Store information about services included in the tour

    // Constructor
    public AttractionTour(int id, String name, String inclution, Map<String, Integer> price) {
        super(id, name, price);
        this.inclution = inclution;
    }

	//Get and set methods
    public String getInclution() {
        return inclution;
    }

    public void setInclution(String inclution) {
        this.inclution = inclution;
    }

    @Override
    public String pickupPlace() {
        return "bus"; //"bus" string
    }
}
