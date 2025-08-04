package assignment2;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StateD extends StateC {
    public static List<Account> accounts = new ArrayList<>(); // List of archives in "Account"
    public static Account currentAccount; //Stores the currently logged in account.

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
       if (tour.pickupPlace() == "bus") {
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
       ticket.setManagerName(currentAccount.getName());

       // Decrease number availability of this tour
       tour.setNumberAvailability(tour.getNumberAvailability() - numberAdult - numberChild);
       return ticket;
   }

    // Override display list tickets method
    @Override
    void displayListTickets(List<Ticket> listTickets) {
        System.out.printf("%-5s %-2s %-40s %-6s %-8s %-10s %-10s %-20s %-10s %-10s %-13s %-10s%n", "Ticket", "Id", "Tour", "Adults", "Children",
                "Total", "Discount", "PickUp/AirBoard", "P&D", "Customer", "Contract number", "Sales Admin");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Ticket reserTicket : listTickets) {
            if(reserTicket.getCustomer() != null) {
                System.out.printf("%-5s %-2d %-40s %-6d %-8d %-10.2f %-10.2f %-20s %-10.2f %-10s %-13s %12s%n", "------", reserTicket.getId(),
                reserTicket.getTourName(), reserTicket.getNumberAdult(), reserTicket.getNumberChild(),
                reserTicket.getTotal(), reserTicket.getDiscount(), reserTicket.getPickUp(), reserTicket.getpAndD(), reserTicket.getCustomer().getName(), reserTicket.getCustomer().getPhoneNumber(), reserTicket.getManagerName());
            } else {
                System.out.printf("%-5s %-2d %-40s %-6d %-8d %-10.2f %-10.2f %-20s %-10s %-10s %-13s %12s%n", "------", reserTicket.getId(),
                    reserTicket.getTourName(), reserTicket.getNumberAdult(), reserTicket.getNumberChild(),
                    reserTicket.getTotal(), reserTicket.getDiscount(), reserTicket.getPickUp(), "--", "--", "--", reserTicket.getManagerName());
            }
        }

        System.out.printf("%-10s %-10.2f%n", "------ Total: $", getTotalTicket(listTickets));
    }

    //Write a list of tickets to a file.
    void writeTicketsToFile(String filename) {
        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(filename))) {
            ois.writeObject(tickets);
            System.out.println("Ticket have been written to the file.");
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    //read list of tickets from a file
    void readTicketsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tickets = (List<Ticket>) ois.readObject();
            System.out.println(accounts.get(0).getName());
        } catch (EOFException e) {
            // If file is empty, create account first
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create Account method
    void createAccounts(Scanner input) {
        System.out.println("No Sales Executives Account Exists. Create new Sales Executives Accounts");
        System.out.print("Enter Admin Username to login: ");
        String adminUsername = input.nextLine();
        System.out.print("Enter Admin Password to login: ");
        String adminPassword = input.nextLine();

        accounts.add(new Account(adminUsername, adminUsername, adminPassword));
        
        do {
            System.out.print("Enter Sales Executives Name: ");
            String name = input.nextLine();
            System.out.print("Enter UserName: ");
            String username = input.nextLine();
            System.out.print("Enter Password: ");
            String password = input.nextLine();

            accounts.add(new Account(name, username, password));

            System.out.print("Do you wish to add one more login? Yes/No?: ");
            String choice = input.nextLine();

            if(choice.equals("No")) {
                break;
            } else if (!choice.equals("Yes") && !choice.equals("No")) {
                System.out.println("Choice is invalid");
                return;
            }
        } while (true);

        writeAccountToFile("C:\\Users\\Administrator\\OneDrive - Swinburne University\\COS10033-Advanced Programming\\104178943_Assignment_2\\assignment2\\data\\account.txt");
    }

    // Save account to file
    void writeAccountToFile(String filename) {
        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(filename))) {
            ois.writeObject(accounts);
            System.out.println("Accounts have been written to the file.");
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Read account from file
    void readAccountFromFile(Scanner input, String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            accounts = (List<Account>) ois.readObject();
            System.out.println(accounts.get(0).getName());
        } catch (EOFException e) {
            // If file is empty, create account first
            createAccounts(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Login method
    static boolean login(Scanner input) {
        System.out.println("Please Login");
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        for(Account acc : accounts) {
            if(acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                // Set current account
                currentAccount = acc;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StateD stateD = new StateD();
        stateD.createTourList();
        stateD.readAccountFromFile(input, "C:\\Users\\Administrator\\OneDrive - Swinburne University\\COS10033-Advanced Programming\\104178943_Assignment_2\\assignment2\\data\\account.txt");

        // Login first
        do {
            boolean isLoginValid = login(input);
            if(!isLoginValid) {
                System.out.println("Login fail");
            } else {
                break;
            }
        } while(true);

        String ticketFileName = "C:\\Users\\Administrator\\OneDrive - Swinburne University\\COS10033-Advanced Programming\\104178943_Assignment_2\\assignment2\\data\\data.txt";
        stateD.readTicketsFromFile(ticketFileName);
        
        while (true) {
            menu(input);
            char choice = input.nextLine().charAt(0);
            int resultAfterChoice = stateD.handleChoice(choice, input);

            // If choice X, the while loop will be broken
            if (resultAfterChoice == 0) {
                // Save ticket before exit
                stateD.writeTicketsToFile(ticketFileName);
                break;
            }    
        }
    }
}
