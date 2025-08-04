
/* Name: Nguyen Phuong Le
 * ID: 104178943
 * Assignment 1
 */



import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

//Create ticket class
class Ticket {
    int id;
    String allocatedSeat;
    int seatNumber;

 // Constructor
    public Ticket(int id, String allocatedSeat, int seatNumber) {
        this.id = id;
        this.allocatedSeat = allocatedSeat;
        this.seatNumber = seatNumber;
    }

  //Get methods
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getAllocatedSeat() {return allocatedSeat;}

    public void setAllocatedSeat(String allocatedSeat) {
    	this.allocatedSeat = allocatedSeat;
    }

  //Set methods
    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket: Id: " + id + ", allocatedSeat: " + allocatedSeat + ", seatNumber: " + seatNumber;
    }

}

//Create TicketService class to create, edit, get Tickets
class TicketService {
	
	//Create an array of ticket
    private List<Ticket> tickets;

 // Contructor
    public TicketService(List<Ticket> tickets) {
        this.tickets = tickets;
    }

 // Getter and setter
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    
    //Create new ticket
    // Methor to create new ticket, parameter is name of seat allocated(allocatedSeat), and number of seat(seatNumber)
    public void createNewTicket(String allocatedSeat, int seatNumber) {
        int ticketId;

        // If array ticket empty, the first ticket have id = 1
        if (this.tickets.isEmpty()) {
            ticketId = 1;
        } else {
        	
        	// If array ticket not empty, the ticket have id = last ticket of array ticket + 1
            Ticket lastTicket = this.tickets.get(tickets.size() - 1);
            ticketId = lastTicket.getId() + 1;
        }

        // Create new ticket and push it in tickets array
        Ticket newTicket = new Ticket(ticketId, allocatedSeat, seatNumber);
        this.tickets.add(newTicket);
        return;
    }
    
    //Search for tickets by ticket ID number
    public Ticket findTicketById(int id) {
        for (Ticket ticket : this.tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    //Print total number of seats booked
    public void printAllTickets() {
        for (Ticket ticket : this.tickets) {
            System.out.println(ticket.toString());
        }
    }
}

/**
 * Main
 */
public class Question_A  {

	// Function to initialize 2D array
    public static void initializeCinema(Boolean[][] cinema) {
        
    	//All seats are empty
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                cinema[i][j] = false; 
            }
        }
    }
    
    //case 2: In the state of the sitting - Function Print all availability seat 
    static void printCurrentAvailability(Boolean[][] cinema) {
    	int row = 8;
    	int colum = 10;
    	char [][]array = new char [row][colum];
    	
        System.out.println("Current Availability: ");
        
        //create from 1 to 10
        System.out.print("  ");
    	for (int i = 1; i <= colum; i++) {
    		System.out.print(i +" ");
    	}
    	System.out.println();
    	
        for (int i = 0; i < 8; i++) {
        	
        	//create from char A to H
        	int a = i + 65;
        	char letter = (char)a;
        	System.out.print(letter);
        	System.out.print(" ");
        	
        	//Number of seats available and booked
            for (int j = 0; j < 10; j++) {
                if (!cinema[i][j]) {
                	// If seat is available, show 'o'
                    System.out.print("o "); //empty seat
                } else {
                	// If seat is not available, show 'x'
                    System.out.print("x "); //seat booked
                }
            }
            System.out.print("\n");
        }
        System.out.print("_______SCREEN______ \n");
    }

    //case 3: count and print the number of empty seats in the cinema
    static int printCountAvailability(Boolean[][] cinema) {
        int count = 0;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
            	// if seat is available, count = count + 1
                if (!cinema[i][j]) {
                    count++;
                }
            }

        }
        System.out.println("" + count + " seats are available for reservation.");
        return count;
    }

    /*Convert position description string to row and column index
    Get seat indicaes
    Example: if user input seat A2 => so we change it to posion cinema[0][1] */
    
    public static int[] getSeatIndices(String seat) {
    	// Get the first chare, in the example rowChar is A
        char rowChar = seat.charAt(0); 
        
        // Get the second number, in the example colNUm is 2
        int colNum = Integer.parseInt(seat.substring(1));
        
        // RowIndex = A - 'A' = 0
        int rowIndex = rowChar - 'A';
        
        // ColIndex = 2 - 1 = 1
        int colIndex = colNum - 1;
        
        // Return [0, 1] => cinema[0][1]
        return new int[] { rowIndex, colIndex };
    }

    //Arrange the seats that the user order
    // Get seat name mean allocatedSeat
    public static String getSeatName(TicketService ticketService, int column, int row, int numberSeat) {
        char rowChar = (char) (row + 'A');
        int columnChar = column + 1;
        String beginSeat = rowChar + "" + columnChar;

        String endSeat = rowChar + "" + (columnChar + numberSeat - 1); //last seat number in row when booking
        ticketService.createNewTicket(beginSeat + " to " + endSeat, numberSeat);
        return beginSeat + " to " + endSeat;
    }

    // If seat is avaible, book seat to change it from false to be true
    static void bookSeat(Boolean[][] cinema, TicketService ticketService, int column, int row, int numberSeat) {
        for (int k = column; k < column + numberSeat; k++) {
            cinema[row][k] = true;
        }
        System.out.println("Seats reserved. " + getSeatName(ticketService, column, row, numberSeat));
        return;
    }

    //case 1: Ticket booking and seat creation process
    static void reverseTicket(Boolean[][] cinema, TicketService ticketService, Scanner input) {
        System.out.print("Enter how many tickets you wish to reserve? ");
        int numberSeat = input.nextInt();

        // Number seat must greater than 0 and less or equal than 10
        if (numberSeat <= 0) {
            System.out.println("Sorry – Minimum 1 tickets can be reserved at a time.");
            return;
        } else if (numberSeat > 10) {
            System.out.println("Sorry – Maximum 10 tickets can be reserved at a time.");
            return;
        }

        System.out.print("Do you wish the system to allocate the seats for you Y/N? ");
        String isAutoSystem = input.next();

        int column = -1;
        int row = -1;

        //System ticket allocation system
        if (isAutoSystem.equals("Y") || isAutoSystem.equals("y")) {
            outerLoop: for (int i = 0; i < 8; i++) {
                int countAvailability = 0; //Number of empty seats in an initial row
                for (int j = 0; j < 10; j++) {

                    if (!cinema[i][j]) //The seat has been booked.
                        countAvailability++;
                    else
                        countAvailability = 0;

                    //Check if there are enough seats for the number of tickets in a row that the user booked
                    if (countAvailability >= numberSeat) {
                        row = i;
                        column = j - numberSeat + 1;
                        break outerLoop;
                    }
                }
            }

            //Seat availability or insufficient seating announcement
            if (column != -1 && row != -1) {
                bookSeat(cinema, ticketService, column, row, numberSeat);
            } else {
                System.out.println("Sorry, no allocation can be made. Insufficient seats in the row");
            }
            return;
        }
        
        //Get seat by user choose
        else if (isAutoSystem.equals("N") || isAutoSystem.equals("n")) {
            System.out.print("Please select the row & seat number that you wish to reserve your seats from: ");
            String beginSeat = input.next();

            int[] seat = getSeatIndices(beginSeat);

            //Check if the seat is available
            int countAvailability = 0; //Number of empty seats in an initial row
            for (int j = seat[1]; j < 10; j++) {
            	// seat [1]: index of columns -> Column index starting from the entered seat number to check
            	// seat [0]: index of rows
                if (!cinema[seat[0]][j])
                    countAvailability++;
                else {
                    System.out.println("Sorry, no allocation can be made. Seats already taken.");
                    return;
                }

                if (countAvailability >= numberSeat) {
                    row = seat[0];
                    column = seat[1];
                    break;
                }
            }

            //Check if there is enough space in the row to allocate seats.
            if (countAvailability >= numberSeat) {
                row = seat[0];
                column = seat[1];
            } else {
                System.out.println("Sorry, no allocation can be made. Insufficient seats in the row");
                return;
            }

            if (column != -1 && row != -1) {
                bookSeat(cinema, ticketService, column, row, numberSeat);
            }
            return;
        } 
        else {
            System.out.println("No option " + isAutoSystem + ", Just have Y or N");
            return;
        }
    }

    //case 4: Enter ticket to find equivalent tickets
    //Function search ticket by ticket id
    public static void searchTicket(Scanner input, TicketService ticketService) {
        System.out.print("Input ticket id: ");
        int ticketId = input.nextInt();
        input.nextLine();

        Ticket ticket = ticketService.findTicketById(ticketId);

        if (ticket == null) {
            System.out.println("No ticket with id " + ticketId);
            return;
        }

        System.out.println(ticket.toString());
    }
    
    //case 5: Print total number of seats booked
    public static void printAllTickets(Scanner input, TicketService ticketService) {
        System.out.println("Tickets: ");
        ticketService.printAllTickets();
    }

    //menu
    public static int menu(Scanner input) {
        System.out.println("/***********************************/");
        System.out.println("1 – Reserve Tickets");
        System.out.println("2 – Show Current Availability");
        System.out.println("3 – Show Count of Availability");
        System.out.println("4 – Search Ticket");
        System.out.println("5 – Print All Tickets");
        System.out.println("6 – Exit");
        System.out.print("Please enter your choice: ");
        int option = input.nextInt();
        return option;
    }

    
    public static void main(String[] args) {
    	// Create a 2D array with boolean
        Boolean[][] cinema = new Boolean[8][10];
        
        // Update 2D array to be false of all position
        initializeCinema(cinema);
        boolean isOpenMenu = true;
        
        // Create new array of Ticket
        List<Ticket> tickets = new ArrayList<Ticket>();
        
        // Create new Ticket serivice instance
        TicketService ticketService = new TicketService(tickets);
        Scanner input = new Scanner(System.in);

        while (isOpenMenu) {
            int clientOption = menu(input);
            switch (clientOption) {
                case 1:
                    reverseTicket(cinema, ticketService, input);
                    break;
                case 2:
                    printCurrentAvailability(cinema);
                    break;
                case 3:
                    printCountAvailability(cinema);
                    break;
                case 4:
                    searchTicket(input, ticketService);
                    break;
                case 5:
                    printAllTickets(input, ticketService);
                    break;

                case 6:
                    // Exit
                    isOpenMenu = false;
                    break;
                default:
                    System.out.println("No option " + clientOption);
                    break;
            }
        }
    }
}
