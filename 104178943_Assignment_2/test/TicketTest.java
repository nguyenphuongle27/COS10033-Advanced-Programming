package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import assignment2.ATour;
import assignment2.AttractionTour;
import assignment2.CityTour;
import assignment2.InternationalTour;
import assignment2.QueenslandTour;

class TicketTest {

	@Test
    public void queenland_and_international_tour_not_have_discount() {
        ATour queenlandTour = new QueenslandTour(10, "Cairns", "Airfare + Accommodation", Map.of("adult", 850, "child", 700), 5);

        ATour internationalTour = new InternationalTour(13, "Pattaya", "Airfare + Accommodation + Breakfast",
        Map.of("adult", 1400, "child", 1150), 7);

        int numberAdult = 2, numberChild = 2;
        assertEquals(3100, queenlandTour.total(numberAdult, numberChild));
        assertEquals(5100, internationalTour.total(numberAdult, numberChild));
        assertEquals(0.0, queenlandTour.disCount(numberAdult, numberChild));
        assertEquals(0.0, internationalTour.disCount(numberAdult, numberChild));
    }
	
	   @Test()
	    public void city_and_attraction_tour_have_discount() {
	        ATour cityTour = new CityTour(1, "Melbourne City", "4 hours", Map.of("adult", 35, "child", 25));
	        ATour attractionTour = new AttractionTour(1, "Great Ocean Road", "Lunch & Supper", Map.of("adult", 35, "child", 25));

	        int numberAdult = 2, numberChild = 2;
	        assertEquals(0.15, cityTour.discountPercent(numberAdult, numberChild));
	        assertEquals(0.15, attractionTour.discountPercent(numberAdult, numberChild));
	        assertEquals(18, cityTour.disCount(numberAdult, numberChild));
	        assertEquals(102, cityTour.total(numberAdult, numberChild));
	        assertEquals(18, attractionTour.disCount(numberAdult, numberChild));
	        assertEquals(102, attractionTour.total(numberAdult, numberChild));
	    }
	   
	    @Test()
	    public void discount_depend_on_number_client() {
	        ATour cityTour = new CityTour(1, "Melbourne City", "4 hours", Map.of("adult", 35, "child", 25));

	        int numberAdult = 2, numberChild = 1, numberChild2 = 2;
	        assertEquals(0.1, cityTour.discountPercent(numberAdult, numberChild));
	        assertEquals(0.15, cityTour.discountPercent(numberAdult, numberChild2));
	        assertEquals(9.5, cityTour.disCount(numberAdult, numberChild));
	        assertEquals(18, cityTour.disCount(numberAdult, numberChild2));
	        assertEquals(85.5, cityTour.total(numberAdult, numberChild));
	        assertEquals(102, cityTour.total(numberAdult, numberChild2));
	    }
	   

}
