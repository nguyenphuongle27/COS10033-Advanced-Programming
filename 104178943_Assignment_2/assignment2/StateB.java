package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StateB extends StateA {
    @Override
    void createTourList() {
        // Add new tours to the "tours" list. Tours include "QueenslandTour" and "InternationalTour"
        super.createTourList();
        tours.add(new QueenslandTour(10, "Cairns", "Airfare + Accommodation", Map.of("adult", 850, "child", 700), 5));
        tours.add(new QueenslandTour(11, "Gold Cost ", "Airfare + Accommodation", Map.of("adult", 600, "child", 450), 5));
        tours.add(new InternationalTour(12, "Phuket", "Airfare + Accommodation + Breakfast",
                Map.of("adult", 1350, "child", 1100), 7));
        tours.add(new InternationalTour(13, "Pattaya", "Airfare + Accommodation + Breakfast",
                Map.of("adult", 1400, "child", 1150), 7));
        tours.add(new InternationalTour(14, "Singapore", "Airfare + Accommodation", Map.of("adult", 1200, "child", 1000),
                7));
    }

    void displayQueenslandTour() {
        for (ATour tour : tours) { //Browse all tours in the "tours" list
            if (tour instanceof QueenslandTour) { //Print tours of type "QueenslandTour"
                tour.printToString();
            }
        }
    }

    void displauInternationalTour() {
        for (ATour tour : tours) { //Browse all tours in the "tours" list
            if (tour instanceof InternationalTour) { //Print tours of type "InternationalTour"
                tour.printToString();
            }
        }
    }

    // Override reserve one ticket method
    @Override
    Ticket reverseOneTicket(Scanner input, int idTicket) {
        System.out.println("\nNWhich type of tour you wish to book");
        System.out.println("\tA: City Tour");
        System.out.println("\tB: Attractions");
        System.out.println("\tC: Queensland");
        System.out.println("\tD: Overseas");
        System.out.print("Please enter your choice: ");
        char choice = input.nextLine().charAt(0);
        System.out.println("Pick your choice");

        switch (choice) {
            case 'A':
                displayCityTour();
                break;
            case 'B':
                displayAttractionTour();
                break;
            case 'C':
                displayQueenslandTour();
                break;
            case 'D':
                displauInternationalTour();
                break;

            default:
                System.out.println("Choice is invalid");
                return null;
        }

        System.out.println("Which tour you wish to buy ticket/s for: ");
        int tourId = input.nextInt();
        input.nextLine();

        ATour tour = null;
        // Find tour with id
        for (ATour atour : tours) {
            if (atour.getId() == tourId) {
                tour = atour;
            }
        }

        if (tour == null) {
            System.out.println("Your tour is invalid");
            return null;
        }

        // Get number adult and child
        System.out.println("Booking tickets for " + tour.getName());
        System.out.println("How many adult tickets - Maximum " + tour.getNumberAvailability());
        int numberAdult = input.nextInt();
        input.nextLine();

        System.out.println("How many child tickets - Maximum " + tour.getNumberAvailability());
        int numberChild = input.nextInt();
        input.nextLine();

        String pickUp = "--";
        double pAndD = 0;
        Customer customer = null;

        // If tour is not overseas
        if (tour.pickupPlace() == "bus") { //"bus" string
            // Get board bus choice
            displayListAttractionBus();
            System.out.println("\nWhere do you wish to board the bus");
            System.out.println("Please enter your choice:");
            int busChoice = input.nextInt();
            input.nextLine();

            // Validate bus choice
            if (busChoice <= 0 || busChoice > 5) {
                System.out.println("Choice is invalid");
                return null;
            }

            pickUp = listAttractionBus[busChoice - 1];
        }
        // If tour is overseas 
        else if (tour.pickupPlace() == "airport") {
            System.out.println("Enter boarding airport");
            pickUp = input.nextLine();
            System.out.println("Add Airport Pickup & Drop at the destination at %150. Y/N?");
            char pickupChoice = input.nextLine().charAt(0);

            // Have Pick And Drop fee
            switch (pickupChoice) {
                case 'Y':
                    pAndD = 150;
                    break;
                case 'N':
                    pAndD = 0;
                    break;
                default:
                    System.out.println("Choice is invalid");
                    return null;
            }
            System.out.println("For interstate/Overseas Travel enter customer name and Contact number");
            System.out.println("Name:");
            String name = input.nextLine();
            System.out.println("Contact Nubmer:");
            String phoneNumber = input.nextLine();
            customer = new Customer(name, phoneNumber);
        }

        // Calculate discount and total
        double discount = tour.disCount(numberAdult, numberChild);
        double total = tour.total(numberAdult, numberChild);

        // Create new Ticket
        Ticket ticket = new Ticket(idTicket, tour.getName(), numberAdult, numberChild, discount,
                total, pickUp);

        if(customer != null) {
            ticket.setpAndD(pAndD);
            ticket.setCustomer(customer);
        }

        // Decrease number availability of this tour
        tour.setNumberAvailability(tour.getNumberAvailability() - numberAdult - numberChild);
        return ticket;
    }

    // Override display list tickets method
    @Override
    void displayListTickets(List<Ticket> listTickets) {
        System.out.printf("%-5s %-2s %-40s %-6s %-8s %-10s %-10s %-20s %-10s %-10s %-13s%n", "Ticket", "Id", "Tour", "Adults", "Children",
                "Total", "Discount", "PickUp/AirBoard", "P&D", "Customer", "Contract number");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Ticket reserTicket : listTickets) {
            if(reserTicket.getCustomer() != null) {
                System.out.printf("%-5s %-2d %-40s %-6d %-8d %-10.2f %-10.2f %-20s %-10.2f %-10s %-13s%n", "------", reserTicket.getId(),
                reserTicket.getTourName(), reserTicket.getNumberAdult(), reserTicket.getNumberChild(),
                reserTicket.getTotal(), reserTicket.getDiscount(), reserTicket.getPickUp(), reserTicket.getpAndD(), reserTicket.getCustomer().getName(), reserTicket.getCustomer().getPhoneNumber());
            } else {
                System.out.printf("%-5s %-2d %-40s %-6d %-8d %-10.2f %-10.2f %-20s%n", "------", reserTicket.getId(),
                    reserTicket.getTourName(), reserTicket.getNumberAdult(), reserTicket.getNumberChild(),
                    reserTicket.getTotal(), reserTicket.getDiscount(), reserTicket.getPickUp());
            }
        }

        System.out.printf("%-10s %-10.2f%n", "------ Total: $", getTotalTicket(listTickets));
    }

    // Show availability of each tour (D choice)
    @Override
    void displayListAvailability() {
        System.out.printf("%-40s %-20s%n", "Tour", "Current Availability");
        for (ATour tour : tours) {
            if(tour instanceof QueenslandTour || tour instanceof InternationalTour) {
                continue;
            }
            System.out.printf("%-40s %-20d%n", tour.getName(), tour.getNumberAvailability());
        }
    }

    //List Sales for a Tour (F choice)
    @Override
    void displayReversedTicketOfTour(Scanner input) {
        displayCityTour();
        displayAttractionTour();
        displayQueenslandTour();
        displauInternationalTour();
        System.out.print("Select the tour number: ");
        int idTour = input.nextInt();
        input.nextLine();

        ATour tour = null;
        for(ATour atour : tours) {
            if(atour.getId() == idTour) tour = atour;
        }

        if(tour == null) {
            System.out.println("Choice is invalid");
            return;
        }

        List<Ticket> listTickets = new ArrayList<>();

        for(Ticket reservedTicket: tickets) {
            if(reservedTicket.getTourName() == tour.getName()) {
                listTickets.add(reservedTicket);
            }
        }
        System.out.println("Reversed Tickets");
        displayListTickets(listTickets);
    }
    
    //main
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StateB stateB = new StateB();
        stateB.createTourList();

        // While loop to display menu always
        while (true) {
            menu(input);
            char choice = input.nextLine().charAt(0);
            int resultAfterChoice = stateB.handleChoice(choice, input);

            // If choice X, the while loop will be broken
            if (resultAfterChoice == 0)
                break;
        }
    }
}
