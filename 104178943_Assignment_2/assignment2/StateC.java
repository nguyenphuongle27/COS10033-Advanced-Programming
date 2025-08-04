package assignment2;

import java.util.Scanner;

public class StateC extends StateB implements IInsurance {
    @Override
    public void createInsuarance(ATour tour, boolean isCreateInsurance) throws InsuranceException {
        // If oversea tour, must have Insurance
    	// If the tour is international without insurance, throw an "InsuranceException"
        if (!isCreateInsurance && tour instanceof InternationalTour) {
            throw new InsuranceException("Overseas Travel requires Insurance cover. Reservation not made");
        }
        // If Victoria (state A) day tour, can not have insurance 
        // If the tour is one of the non-insurance types and the user chooses insurance, also throws an "InsuranceException" exception
        else if (isCreateInsurance && (tour instanceof CityTour || tour instanceof AttractionTour)) {
            throw new InsuranceException("Victoria Day Tour no need Insurance cover.");
        }
    }

    @Override
    public double getPriceInsuarance(int numberClient) {
        // Single person
        if(numberClient == 1) return 200;
        // Couple
        else if (numberClient == 2) return 350;
        // Family
        else if(numberClient > 2 && numberClient <= 5) return 500; 
        else if(numberClient <= 0) return 0;
        else {
            // Over 5 person
            return 200 * numberClient;
        }
    }

    // Override reserve one ticket method
    @Override
    Ticket reverseOneTicket(Scanner input, int idTicket) {
        System.out.println("Which type of tour you wish to book");
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
        if (tour.pickupPlace() == "bus") {  //"bus" string
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

        // Create Insurance
        double insurancePrice = getPriceInsuarance(numberAdult + numberChild);
        System.out.println("The tour is being planned for " + (numberAdult + numberChild) + " guest/s: This requires insurne cover for : $" + insurancePrice);
        System.out.println("Confirm insurance cover for " + insurancePrice + ". Y/N ?");

        char insuranceChoice = input.nextLine().charAt(0);
        boolean changeToBoolean = false;

        switch (insuranceChoice) {
            case 'Y':
                changeToBoolean = true;
                break;
            case 'N':
                changeToBoolean = false;
                break;
            default:
                System.out.println("Choice is invalid");
                return null;
        }

        // Handling exception
        try {
            createInsuarance(tour, changeToBoolean);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StateC stateC = new StateC();
        stateC.createTourList();

        // While loop to display menu always
        while (true) {
            menu(input);
            char choice = input.nextLine().charAt(0);
            int resultAfterChoice = stateC.handleChoice(choice, input);

            // If choice X, the while loop will be broken
            if (resultAfterChoice == 0)
                break;
        }
    }
}
