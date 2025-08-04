package week9;

import java.io.*;
import java.util.*;

class Customer {
    private String firstname;
    private String lastname;
    private String address;
    private Account account;

    public Customer(String first, String last, String add) {
        this.firstname = first;
        this.lastname = last;
        this.address = add;
    }

    public String getFirst() { return firstname; }
    public String getLast() { return lastname; }
    public String getAddress() { return address; }
    public Account getAccount() { return account; }

    public void setFirst(String first) { this.firstname = first; }
    public void setLast(String last) { this.lastname = last; }
    public void setAddress(String add) { this.address = add; }
    public void setAccount(Account account) { this.account = account; }

    @Override
    public String toString() {
        String accountDetails = (account != null) ? account.toString() : "No Account";
        return "Firstname: " + firstname + ", Lastname: " + lastname + ", Address: " + address + ", " + accountDetails;
    }
}

class Account {
    private String accountID;
    private String type;
    private double balance;

    public Account(String id, String type, double amount) {
        this.accountID = id;
        this.type = type;
        this.balance = amount;
    }

    public String getId() { return accountID; }
    public String getType() { return type; }
    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return "AccountID: " + accountID + ", Type: " + type + ", Balance: $" + balance;
    }
}

public class task9_1 {
    private static int customercount = 0;
    private static Customer[] customers = new Customer[3];

    private static void Menu() {
        System.out.println("1. Create Customer");
        System.out.println("2. Create Account");
        System.out.println("3. Print Customer Details");
        System.out.println("4. Exit");
    }

    private static void addCustomer(Scanner input) {
        if (customercount < customers.length) {
            System.out.println("Enter the details");
            System.out.print("Firstname: ");
            String first = input.nextLine();
            System.out.print("Lastname: ");
            String last = input.nextLine();
            System.out.print("Address: ");
            String add = input.nextLine();

            customers[customercount++] = new Customer(first, last, add);
        } else {
            System.out.println("Customer array is full.");
        }
    }

    private static void addAccount(Scanner input) {
        System.out.print("Enter the Customer's firstname: ");
        String firstname = input.nextLine();
        for (Customer customer : customers) {
            if (customer != null && customer.getFirst().equals(firstname)) {
                if (customer.getAccount() != null) {
                    System.out.println("This customer already has an account.");
                    return;
                }
                System.out.print("Account ID: ");
                String id = input.nextLine();
                System.out.print("Account Type: ");
                String type = input.nextLine();
                System.out.print("Initial Balance: $");
                double bal = input.nextDouble();
                input.nextLine(); // Consume the newline

                Account account = new Account(id, type, bal);
                customer.setAccount(account);
                System.out.println("Account created for " + customer.getFirst());
                return;
            }
        }
        System.out.println("Customer not found.");
    }

    private static void printdetails() {
        System.out.println("Customer Details:");
        for (int i = 0; i < customercount; i++) {
            System.out.println(customers[i].toString());
        }
    }

    private static void readFromFile() {
        try (Scanner filein = new Scanner(new FileReader("readme.txt"))) {
            customercount = 0;
            while (filein.hasNextLine() && customercount < customers.length) {
                String[] data = filein.nextLine().split("\t");
                Customer customer = new Customer(data[0], data[1], data[2]);
                if (data.length > 3) {
                    Account account = new Account(data[3], data[4], Double.parseDouble(data[5]));
                    customer.setAccount(account);
                }
                customers[customercount++] = customer;
            }
        } catch (IOException e) {
            System.out.println("Could not open file for reading!");
        }
    }

    private static void saveToFile() {
        try (PrintWriter objOut = new PrintWriter(new FileWriter("readme.txt"))) {
            for (int i = 0; i < customercount; i++) {
                Customer customer = customers[i];
                objOut.print(customer.getFirst() + "\t" + customer.getLast() + "\t" + customer.getAddress());
                if (customer.getAccount() != null) {
                    Account account = customer.getAccount();
                    objOut.print("\t" + account.getId() + "\t" + account.getType() + "\t" + account.getBalance());
                }
                objOut.println();
            }
            System.out.println("Customer details saved.");
        } catch (IOException e) {
            System.out.println("Could not open file for writing!");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        readFromFile();

        int choice;
        do {
            Menu();
            System.out.print("Please Select your choice: ");
            choice = input.nextInt();
            input.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    addCustomer(input);
                    break;
                case 2:
                    addAccount(input);
                    break;
                case 3:
                    printdetails();
                    break;
                case 4:
                    saveToFile();
                    System.out.println("Exiting Program");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        } while (choice != 4);

        input.close();
    }
}
