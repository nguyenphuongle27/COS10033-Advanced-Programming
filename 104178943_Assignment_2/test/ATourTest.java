package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import assignment2.ATour;
import assignment2.AttractionTour;
import assignment2.CityTour;
import assignment2.InternationalTour;
import assignment2.QueenslandTour;

class ATourTest {

    @Test()
    public void test_create_city_tour() {
        ATour tour = new CityTour(1, "Melbourne City", "4 hours", Map.of("adult", 35, "child", 25));
        // Test name, id, duration
        Assert.assertEquals(tour.getId(), 1);
        Assert.assertEquals(tour.getName(), "Melbourne City");
        Assert.assertEquals(tour.pickupPlace(), null);
    }

    @Test()
    public void test_create_attraction_tour() {
        ATour tour = new AttractionTour(1, "Great Ocean Road", "Lunch & Supper", Map.of("adult", 35, "child", 25));
        // Test name, id, duration
        Assert.assertEquals(tour.getId(), 1);
        Assert.assertEquals(tour.getName(), "Great Ocean Road");
        Assert.assertEquals(tour.pickupPlace(), "bus");
    }

    @Test()
    public void test_create_queenland_tour() {
        ATour tour = new QueenslandTour(10, "Cairns", "Airfare + Accommodation", Map.of("adult", 850, "child", 700), 5);
        // Test name, id, duration
        Assert.assertEquals(tour.getId(), 10);
        Assert.assertEquals(tour.getName(), "Cairns");
        Assert.assertEquals(tour.pickupPlace(), "airport");
    }

    @Test()
    public void test_create_international_tour() {
        ATour tour = new InternationalTour(13, "Pattaya", "Airfare + Accommodation + Breakfast",
        Map.of("adult", 1400, "child", 1150), 7);
        // Test name, id, duration
        Assert.assertEquals(tour.getId(), 13);
        Assert.assertEquals(tour.getName(), "Pattaya");
        Assert.assertEquals(tour.pickupPlace(), "airport");
    }

}
