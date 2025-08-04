package test3;
import java.util.*;

abstract class Vacation {
    private static int idCounter = 1;
    private int vacationId;
    private double cost;

    public Vacation(double cost) {
        this.vacationId = idCounter++;
        this.cost = cost;
    }

    public int getVacationId() {
        return this.vacationId;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public abstract void displayTravelMode();

    @Override
    public String toString() {
        return "Vacation ID: " + vacationId + " Cost: $" + cost;
    }
}

class CruiseVacation extends Vacation {
    private String shipName;
    private String departurePort;
    private String arrivalPort;
    private String date;

    public CruiseVacation(double cost, String shipName, String departurePort, String arrivalPort, String date) {
        super(cost);
        this.shipName = shipName;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.date = date;
    }

    // Getters and setters
    public String getShipName() { return shipName; }
    public void setShipName(String shipName) { this.shipName = shipName; }
    public String getDeparturePort() { return departurePort; }
    public void setDeparturePort(String departurePort) { this.departurePort = departurePort; }
    public String getArrivalPort() { return arrivalPort; }
    public void setArrivalPort(String arrivalPort) { this.arrivalPort = arrivalPort; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public void displayTravelMode() {
        System.out.println("Holiday in a cruise");
    }

    @Override
    public String toString() {
        return super.toString() + " Ship: " + shipName + " From: " + departurePort + " To: " + arrivalPort + " Date: " + date;
    }
}

class FlightVacation extends Vacation {
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String date;

    public FlightVacation(double cost, String airline, String departureAirport, String arrivalAirport, String date) {
        super(cost);
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.date = date;
    }

    // Getters and setters
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getDepartureAirport() { return departureAirport; }
    public void setDepartureAirport(String departureAirport) { this.departureAirport = departureAirport; }
    public String getArrivalAirport() { return arrivalAirport; }
    public void setArrivalAirport(String arrivalAirport) { this.arrivalAirport = arrivalAirport; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public void displayTravelMode() {
        System.out.println("Travel in Air");
    }

    @Override
    public String toString() {
        return super.toString() + " Airline: " + airline + " From: " + departureAirport + " To: " + arrivalAirport + " Date: " + date;
    }
}

public class VacationManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Vacation> vacations = new ArrayList<>();
        int choice = 0;

        do {
            System.out.println("\n=== Vacation Management System ===");
            System.out.println("1 - Add New Vacation");
            System.out.println("2 - Display All Vacations");
            System.out.println("3 - Search Vacation");
            System.out.println("4 - Delete Vacation");
            System.out.println("5 - Display Most Expensive Vacations");
            System.out.println("6 - Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addVacation(scanner, vacations);
                    break;
                case 2:
                    displayVacations(vacations);
                    break;
                case 3:
                    searchVacation(scanner, vacations);
                    break;
                case 4:
                    deleteVacation(scanner, vacations);
                    break;
                case 5:
                    displayMostExpensiveVacations(vacations);
                    break;
                case 6:
                    System.out.println("Thank you for using the Vacation Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void addVacation(Scanner scanner, ArrayList<Vacation> vacations) {
        System.out.print("Enter vacation type (1 for Cruise, 2 for Flight): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter vacation cost: $");
        double cost = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (type == 1) {
            System.out.print("Enter ship name: ");
            String shipName = scanner.nextLine();
            System.out.print("Enter departure port: ");
            String departurePort = scanner.nextLine();
            System.out.print("Enter arrival port: ");
            String arrivalPort = scanner.nextLine();
            System.out.print("Enter date: ");
            String date = scanner.nextLine();

            vacations.add(new CruiseVacation(cost, shipName, departurePort, arrivalPort, date));
        } else if (type == 2) {
            System.out.print("Enter airline: ");
            String airline = scanner.nextLine();
            System.out.print("Enter departure airport: ");
            String departureAirport = scanner.nextLine();
            System.out.print("Enter arrival airport: ");
            String arrivalAirport = scanner.nextLine();
            System.out.print("Enter date: ");
            String date = scanner.nextLine();

            vacations.add(new FlightVacation(cost, airline, departureAirport, arrivalAirport, date));
        } else {
            System.out.println("Invalid vacation type.");
            return;
        }

        System.out.println("Vacation added successfully.");
    }

    private static void displayVacations(ArrayList<Vacation> vacations) {
        if (vacations.isEmpty()) {
            System.out.println("No vacations found.");
        } else {
            for (Vacation vacation : vacations) {
                System.out.println(vacation);
                vacation.displayTravelMode();
            }
        }
    }

    private static void searchVacation(Scanner scanner, ArrayList<Vacation> vacations) {
        System.out.print("Enter Vacation ID to search: ");
        int id = scanner.nextInt();

        for (Vacation vacation : vacations) {
            if (vacation.getVacationId() == id) {
                System.out.println(vacation);
                vacation.displayTravelMode();
                return;
            }
        }

        System.out.println("Vacation not found.");
    }

    private static void deleteVacation(Scanner scanner, ArrayList<Vacation> vacations) {
        System.out.print("Enter Vacation ID to delete: ");
        int id = scanner.nextInt();

        for (int i = 0; i < vacations.size(); i++) {
            if (vacations.get(i).getVacationId() == id) {
                vacations.remove(i);
                System.out.println("Vacation deleted successfully.");
                return;
            }
        }

        System.out.println("Vacation not found.");
    }

    private static void displayMostExpensiveVacations(ArrayList<Vacation> vacations) {
        if (vacations.isEmpty()) {
            System.out.println("No vacations found.");
            return;
        }

        double maxCost = vacations.stream().mapToDouble(Vacation::getCost).max().orElse(0);

        System.out.println("Most expensive vacation(s):");
        for (Vacation vacation : vacations) {
            if (vacation.getCost() == maxCost) {
                System.out.println(vacation);
                vacation.displayTravelMode();
            }
        }
    }
}