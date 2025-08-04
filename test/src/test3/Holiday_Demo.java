package test3;

import java.util.*;

abstract class Holiday {
    private static int counter = 1;
    private int holidayNo;
    private double amount;

    public Holiday(double amount) {
        this.holidayNo = counter++;
        this.amount = amount;
    }

    public int getHolidayNo() {
        return holidayNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract void TravelMode();

    @Override
    public String toString() {
        return "Holiday No: " + holidayNo + " Amount: " + amount;
    }
}

class BySea extends Holiday {
    private String cruiseName;
    private String startingPort;
    private String destinationPort;
    private String date;

    public BySea(double amount, String cruiseName, String startingPort, String destinationPort, String date) {
        super(amount);
        this.cruiseName = cruiseName;
        this.startingPort = startingPort;
        this.destinationPort = destinationPort;
        this.date = date;
    }

    public String getCruiseName() { return cruiseName; }
    public void setCruiseName(String cruiseName) { this.cruiseName = cruiseName; }
    public String getStartingPort() { return startingPort; }
    public void setStartingPort(String startingPort) { this.startingPort = startingPort; }
    public String getDestinationPort() { return destinationPort; }
    public void setDestinationPort(String destinationPort) { this.destinationPort = destinationPort; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public void TravelMode() {
        System.out.println("Holiday in a cruise");
    }

    @Override
    public String toString() {
        return super.toString() + " Cruise: " + cruiseName + " From: " + startingPort + " To: " + destinationPort + " Date: " + date;
    }
}

class ByAir extends Holiday {
    private String flightName;
    private String departureAirport;
    private String arrivalAirport;
    private String date;

    public ByAir(double amount, String flightName, String departureAirport, String arrivalAirport, String date) {
        super(amount);
        this.flightName = flightName;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.date = date;
    }

    public String getFlightName() { return flightName; }
    public void setFlightName(String flightName) { this.flightName = flightName; }
    public String getDepartureAirport() { return departureAirport; }
    public void setDepartureAirport(String departureAirport) { this.departureAirport = departureAirport; }
    public String getArrivalAirport() { return arrivalAirport; }
    public void setArrivalAirport(String arrivalAirport) { this.arrivalAirport = arrivalAirport; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public void TravelMode() {
        System.out.println("Travel in Air");
    }

    @Override
    public String toString() {
        return super.toString() + " Flight: " + flightName + " From: " + departureAirport + " To: " + arrivalAirport + " Date: " + date;
    }
}

public class Holiday_Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Holiday> collection = new ArrayList<>();
        int option = 0;

        do {
            System.out.println("1 - New Holiday");
            System.out.println("2 - Display All Holidays");
            System.out.println("3 - Search for a Holiday");
            System.out.println("4 - Delete a Holiday");
            System.out.println("5 - Display Holiday with Maximum Amount");
            System.out.println("6 - Exit");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter 1 for BySea or 2 for ByAir:");
                    int holidayType = sc.nextInt();
                    System.out.println("Enter Holiday Amount");
                    double amount = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    if (holidayType == 1) {
                        System.out.println("Enter Cruise Name");
                        String cruiseName = sc.nextLine();
                        System.out.println("Enter Starting Port");
                        String startingPort = sc.nextLine();
                        System.out.println("Enter Destination Port");
                        String destinationPort = sc.nextLine();
                        System.out.println("Enter Date");
                        String date = sc.nextLine();
                        collection.add(new BySea(amount, cruiseName, startingPort, destinationPort, date));
                    } else {
                        System.out.println("Enter Flight Name");
                        String flightName = sc.nextLine();
                        System.out.println("Enter Departure Airport");
                        String departureAirport = sc.nextLine();
                        System.out.println("Enter Arrival Airport");
                        String arrivalAirport = sc.nextLine();
                        System.out.println("Enter Date");
                        String date = sc.nextLine();
                        collection.add(new ByAir(amount, flightName, departureAirport, arrivalAirport, date));
                    }
                    break;
                case 2:
                    if (!collection.isEmpty()) {
                        for (Holiday holiday : collection) {
                            System.out.println(holiday);
                            holiday.TravelMode();
                        }
                    } else {
                        System.out.println("Holiday Collection is Empty");
                    }
                    break;
                case 3:
                    System.out.println("Enter Holiday No to Search");
                    int searchNo = sc.nextInt();
                    boolean found = false;
                    for (Holiday holiday : collection) {
                        if (holiday.getHolidayNo() == searchNo) {
                            System.out.println(holiday);
                            holiday.TravelMode();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Holiday Not Found");
                    }
                    break;
                case 4:
                    System.out.println("Enter Holiday No to Delete");
                    int deleteNo = sc.nextInt();
                    Iterator<Holiday> iterator = collection.iterator();
                    boolean deleted = false;
                    while (iterator.hasNext()) {
                        Holiday holiday = iterator.next();
                        if (holiday.getHolidayNo() == deleteNo) {
                            iterator.remove();
                            deleted = true;
                            System.out.println("Holiday Deleted");
                            break;
                        }
                    }
                    if (!deleted) {
                        System.out.println("Holiday Not Found");
                    }
                    break;
                case 5:
                    if (!collection.isEmpty()) {
                        double maxAmount = collection.stream().mapToDouble(Holiday::getAmount).max().orElse(0);
                        System.out.println("Holidays with Maximum Amount:");
                        for (Holiday holiday : collection) {
                            if (holiday.getAmount() == maxAmount) {
                                System.out.println(holiday);
                                holiday.TravelMode();
                            }
                        }
                    } else {
                        System.out.println("Holiday Collection is Empty");
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (option != 6);

        sc.close();
    }
}
