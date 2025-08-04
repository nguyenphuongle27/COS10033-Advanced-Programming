package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StateA {
    public static List<ATour> tours = new ArrayList<>(); //Declare the "tours" list
    public static List<Ticket> tickets = new ArrayList<>(); //declare a "tickets" list to store booked tickets
    public static String[] listAttractionBus = {"Travel Australia CBD office", "Flinders Station", "Southern Cross Station", "Queen Victoria Market", "Melbourne Museum"};

    // Create Tour list
    void createTourList() {
    	// Add city tour
    	
    	/* 	Map -> contains ticket price information for adults and children.
        	Create a list of tours, including "CityTour" and "AttractionTour" */
        tours.add(new CityTour(1, "Melbourne City", "4 hours", Map.of("adult", 35, "child", 25)));
        tours.add(new CityTour(2, "Melbourne City & Yarra River Boat Cruise", "6 hours",
                Map.of("adult", 65, "child", 40)));
        tours.add(new CityTour(3, "Yarra River Cruise & Melbourne Zoo", "6 hours", Map.of("adult", 75, "child", 45)));
        tours.add(new CityTour(4, "Melbourne City & Melbourne Zoo", "7 hours", Map.of("adult", 65, "child", 40)));
        tours.add(new CityTour(5, "Melbourne City & Melbourne Aquarium", "6 hours", Map.of("adult", 75, "child", 45)));

        // Add attractions tour
        tours.add(new AttractionTour(6, "Great Ocean Road", "Lunch & Supper", Map.of("adult", 135, "child", 90)));
        tours.add(new AttractionTour(7, "Yarra Valley Wine Tasting ", "Lunch", Map.of("adult", 85, "child", 60)));
        tours.add(new AttractionTour(8, "Wilson Prom ", "Lunch & Evening Tea", Map.of("adult", 110, "child", 90)));
        tours.add(new AttractionTour(9, "Phillip Island & Penguin Parade", "Lunch & Supper",
                Map.of("adult", 160, "child", 120)));
    }

    // Display All City Tour
    void displayCityTour() {
        for (ATour tour : tours) { //Browse all tours in the "tours" list
            if (tour instanceof CityTour) { //Print tours of type "CityTour"
                tour.printToString();
            }
        }
    }

    // Display All Attraction Tour
    void displayAttractionTour() {
        for (ATour tour : tours) { //Browse all tours in the "tours" list
            if (tour instanceof AttractionTour) { //Print tours of type "AttractionTour"
                tour.printToString();
            }
        }
    }

    // Display Attraction Tour Bus
    void displayListAttractionBus() {
        for(int i = 1; i <= 5; i++) { //corresponding to 5 locations in the bus list
            System.out.println("" + i + ". " + listAttractionBus[i - 1]);
        }
    }

    // Calculate total of list tickets
    double getTotalTicket(List<Ticket> listTickets) {
        double total = 0; //total amount of all original tickets
        for(Ticket ticket : listTickets) {
            total += ticket.getTotal(); //Add the "total" value of each ticket to the "total" variable
        }

        return total;
    }


    // Reserve 1 Ticket
    Ticket reverseOneTicket(Scanner input, int idTicket) {
        System.out.println("\nWhich type of tour you wish to book");
        System.out.println("\tA: City Tour");
        System.out.println("\tB: Attractions");
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
            default:
                System.out.println("Choice is invalid");
                return null;
        }

        //Find and book tours
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

                String pickUp = " ";

                if(tour.pickupPlace() == "bus") { //"bus" string
                    // Get board bus choice
                    displayListAttractionBus();
                    System.out.println("\nWhere do you wish to board the bus");
                    System.out.println("Please enter your choice:");
                    int busChoice = input.nextInt();
                    input.nextLine();

                    // Validate bus choice
                    if(busChoice <= 0 || busChoice > 5) {
                        System.out.println("Choice is invalid");
                        return null;
                    }

                    pickUp = listAttractionBus[busChoice - 1];
                }

                

                // Calculate discount and total
                double discount = tour.disCount(numberAdult, numberChild); //Discounts are calculated based on the number of adults and children participating in the tour.
                double total = tour.total(numberAdult, numberChild); //Calculate the total ticket price based on the number of adults and children.

                // Create new Ticket
                Ticket ticket = new Ticket(idTicket, tour.getName(), numberAdult, numberChild, discount,
                total, pickUp);

                // Decrease number availability of this tour
                tour.setNumberAvailability(tour.getNumberAvailability() - numberAdult - numberChild);
                return ticket;
    }

    // Reserve Ticket (can reserve multiple ticket) (A Choice)
    void reverseTicket(Scanner input) {
        List<Ticket> reserveTickets = new ArrayList<>();
        boolean multipleReserve = false;
        do {
            Ticket ticket = reverseOneTicket(input, tickets.size() + reserveTickets.size() + 1);
            if (ticket == null)
                return;
            reserveTickets.add(ticket);
            System.out.println("Reserve another tour/attraction ticket to this customer? Y/N");
            char choice = input.nextLine().charAt(0);

            switch (choice) {
                case 'Y':
                    multipleReserve = true;
                    break;
                case 'N':
                    multipleReserve = false;
                    break;
                default:
                    System.out.println("Choice is invalid");
                    return;
            }
        } while (multipleReserve);

        displayListTickets(reserveTickets);
        for (Ticket reserTicket : reserveTickets) {
            tickets.add(reserTicket);
        }
        return;
    }

    // Show availability of each tour (D choice)
    void displayListAvailability() {
        System.out.printf("%-40s %-20s%n", "\nTour", "Current Availability");
        for (ATour tour : tours) {
            System.out.printf("%-40s %-20d%n", tour.getName(), tour.getNumberAvailability());
        }
    }

    // Show sales of ticket (E and F choice)
    //Displays details of booked tickets.
    void displayListTickets(List<Ticket> listTickets) {
        System.out.printf("%-5s %-2s %-40s %-10s %-10s %-10s %-10s %10s%n", "Ticket", "Id", "Tour", "Adults", "Children",
                "Total", "Discount", "PickUp");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");
        for (Ticket reserTicket : listTickets) {
            System.out.printf("%-5s %-2d %-40s %3d %10d %13.2f %10.2f %27s%n", "------", reserTicket.getId(),
                    reserTicket.getTourName(), reserTicket.getNumberAdult(), reserTicket.getNumberChild(),
                    reserTicket.getTotal(), reserTicket.getDiscount(), reserTicket.getPickUp());
        }

        System.out.printf("%-10s %-10.2f%n", "------ Total: $", getTotalTicket(listTickets));
    }

    // Choice E
    //Show total number of tickets sold
    void displayAllSales() {
        System.out.println("Reversed Tickets");
        displayListTickets(tickets);
    }

    // Display all ticket by tour (Choice F)
    void displayReversedTicketOfTour(Scanner input) {
        displayCityTour();
        displayAttractionTour();
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

    // Search Ticket (Choice B)
    void searchTicket(Scanner input) {
        System.out.println("Enter the ticket number");
        int idTicket = input.nextInt();
        input.nextLine();

        for(Ticket ticket : tickets) {
            if(ticket.getId() == idTicket) {
                System.out.println(ticket.toString());
                break;
            }
        }

        return;
    }

    // Refund ticket method
    void refundOneTicket(Ticket ticket) {
        ticket.setDiscount(0);
        ticket.setTotal(ticket.getTotal() * 0.25); //25% of the amount will be lost when cancelling the ticket.
        ticket.setStatus("Canceled"); //change ticket status

        // Find the tour of ticket
        for(ATour tour: tours) {
            if(tour.getName().equals(ticket.getTourName())) {
                // Update number availability of this tour
                tour.setNumberAvailability(tour.getNumberAvailability() + ticket.getNumberAdult() + ticket.getNumberChild());
            }
        }
    }

    // Choice C
    void refundTicket(Scanner input) {
        System.out.println("Enter the ticket number for refund");
        int idTicket = input.nextInt();
        input.nextLine();

        Ticket reservedTicket = null;

        for(Ticket ticket : tickets) {
            if(ticket.getId() == idTicket) {
                reservedTicket = ticket;
            }
        }

        if(reservedTicket == null) {
            System.out.println("No ticket found");
            return;
        }

        reservedTicket.toString();

        // Calculate total refund ( 75% )
        double refundTotal = reservedTicket.getTotal() * 0.75; ////75% of the amount the customer will receive after canceling the ticket
        System.out.println("Do you wish to cancel this ticket? By cancelling you will receive " + refundTotal + " as refund");
        System.out.println("Press Y to cancel or any key to abort cancellation");
        char agreeChoice = input.nextLine().charAt(0);

        if(agreeChoice == 'Y') {
            refundOneTicket(reservedTicket);
            System.out.println("Ticket cancelled. Your refund is $ " + refundTotal);
            return;
        } else {
            return;
        }
    }

    // Function create menu
    static void menu(Scanner input) {
        System.out.println("\nTravel Australia - Ticket Reservation");
        System.out.println("\tA: Reserver Ticket");
        System.out.println("\tB: Search Ticket");
        System.out.println("\tC: Refund Ticket");
        System.out.println("\tD: List Availability");
        System.out.println("\tE: List Sales");
        System.out.println("\tF: List Sales for a Tour");
        System.out.println("\tX: Exit");
        System.out.print("Please enter your choice: ");
        System.out.print("");
    }

    // Handle Client Choice
    int handleChoice(char choice, Scanner input) {

        switch (choice) {
            case 'A':
                reverseTicket(input);
                break;
            case 'B':
                searchTicket(input);
                break;
            case 'C':
                refundTicket(input);
                break;
            case 'D':
                displayListAvailability();
                break;
            case 'E':
                displayAllSales();
                break;
            case 'F':
                displayReversedTicketOfTour(input);
                break;
            case 'X':
                return 0;
            default:
                System.out.println("Your choice is invalid");
                break;
        }

        return 1;
    }

    // Main function
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StateA stateA = new StateA();
        stateA.createTourList();

        // While loop to display menu always
        while (true) {
            menu(input);
            char choice = input.nextLine().charAt(0);
            int resultAfterChoice = stateA.handleChoice(choice, input);

            // If choice X, the while loop will be broken
            if (resultAfterChoice == 0)
                break;
        }
    }
}
