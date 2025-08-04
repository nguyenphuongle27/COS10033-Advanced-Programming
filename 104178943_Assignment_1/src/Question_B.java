
/* Name: Nguyen Phuong Le
 * ID: 104178943
 * Assignment 1
 */


import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//Creat enum for cover type, other is not valid
enum CoverType {
    Vehicle,
    Health,
    Travel,
    Property,
    Pet,
}

//Class customer
class Customer {
	// Max customer's policy is 5
    private static final int MAX_POLICY_NUMBER = 5;
    private long customerId;
    private String name;
    private String address;
    
    // new array list of policies
    private ArrayList<Policy> policies = new ArrayList<Policy>(MAX_POLICY_NUMBER);

 // Constructor
    public Customer(long customerId, String name, String address) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
    }
    
  //Get methods and Set methods
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static int getMaxPolicyNumber() {
        return MAX_POLICY_NUMBER;
    }

    public ArrayList<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(ArrayList<Policy> policies) {
        this.policies = policies;
    }

 // To string
    @Override
    public String toString() {
        return "Customer_Id: C" + customerId + ", Name: " + name + ", Address: " + address;
    }
}

//Class policy
class Policy {
    private long policyId;
    private long customerId;
    private CoverType coverType;
    private long coverValue;
    private String duration;
    private long installmentAmount;
    private String paymentPlan;
    private Date startDate;
    private boolean isActive;


    //Constructor
    public Policy(long policyId, long customerId, CoverType coverType, long coverValue, String duration,
            long installmentAmount, String paymentPlan, Date startDate) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.coverType = coverType;
        this.coverValue = coverValue;
        this.duration = duration;
        this.installmentAmount = installmentAmount;
        this.paymentPlan = paymentPlan;
        this.startDate = startDate;
        this.isActive = true;
    }

    //Get methods and Set methods
    public long getPolicyId() {return policyId;}

    public void setPolicyId(long policyId) {this.policyId = policyId;}

    public long getCustomerId() {return customerId;}

    public void setCustomerId(long customerId) {this.customerId = customerId;}

    public CoverType getCoverType() {return coverType;}

    public void setCoverType(CoverType coverType) {this.coverType = coverType;}

    public long getCoverValue() {return coverValue;}

    public void setCoverValue(long coverValue) {this.coverValue = coverValue;}

    public String getDuration() {return duration;}

    public void setDuration(String duration) {this.duration = duration;}

    public long getInstallmentAmount() {return installmentAmount;}

    public void setInstallmentAmount(long installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getPaymentPlan() {return paymentPlan;}

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public Date getStartDate() {return startDate;}

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    //format the date to "YYYY/MM/DD" format
    public String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        // Date format and in console out
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

 // To string
    @Override
    public String toString() {
        return "Policy_ID: P" + policyId + ", Customer_ID: " + customerId + ", Cover Type: " + coverType
                + ", Cover Value: $" + coverValue + ", Duration: " + duration + ", Installment Amount: $" + installmentAmount
                + ", Payment Plan: " + paymentPlan + ", Start Date: " + formatDate(startDate) + ", Is Active: " + isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

}

//Create class customer service to create new customer, update customer, delete customer and get customers
class CustomerService {
	// An Array list of customers
    private List<Customer> customers;

    //Constructor
    public CustomerService(List<Customer> customers) {
        this.customers = customers;
    }

    // Getter and Setter
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    // Creation of new Customers [case 1]
    public void createNewCustomer(String customerName, String address) {
        long customerId;

        // If array customer is empty, new customer id is 1001
        if (this.customers.isEmpty()) {
            customerId = 1001;
        } else {
            // If array customer have some customer, new customer id is last customer id in array + 1
            Customer lastCustomer = this.customers.get(this.customers.size() - 1);
            customerId = lastCustomer.getCustomerId() + 1;
        }
        Customer newCustomer = new Customer(customerId, customerName, address);
        this.customers.add(newCustomer);
    }

    // Print all customer in arrays
    //Check if there are any customers in the class Customer [case 2]
    public void printAllCustomer(boolean isPrintPolicy) {
        System.out.println("List customers:");
        if (customers.isEmpty()) {
        	//Empty list
            System.out.println("Empty customer!!!");
            return;
        }
        for (Customer customer : customers) {
            System.out.println(customer.toString());

            // If user want to print policy of customer, print array policies of each customer
            //Check if there are any customers in the class Policy
            if (isPrintPolicy) {
                if (customer.getPolicies().isEmpty()) {
                	//Empty list
                    System.out.println("\tPolicies: None");//false
                } else {
                    for (Policy policy : customer.getPolicies()) {
                        System.out.println("\t\t" + policy.toString());//true
                    }
                }
            }
        }
    }

    //Search for a customer in the customer list by customer ID [case 5]
    public Customer findCustomerById(String customerId) {
    	//Iterate over all Customer objects in the customers list.
        for (Customer customer : customers) {
            if (customerId.equals("C" + customer.getCustomerId())) {
                return customer;
            }
        }
        return null;
    }

    //Search for a customer in the customer list by customer Name
    public List<Customer> findCustomerByName(String customerName) {
        List<Customer> listCustomer = new ArrayList<Customer>();

        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                listCustomer.add(customer);
            }
        }

        return listCustomer;
    }

    //Search for a customer based on the policy ID
    public Customer findByPolicyId(String policyId) {
        for (Customer customer : customers) {
            List<Policy> policies = customer.getPolicies();
            for (Policy policy : policies) {
                if (policyId.equals("P" + policy.getPolicyId())) {
                    return customer;
                }
            }
        }

        return null;
    }
}

//Create class policy service to create new policy, update policy, delete policy and get policies
class PolicyService {
	// An Array list of policies
    private List<Policy> policies;

    // Constructor
    public PolicyService(List<Policy> policies) {
        this.policies = policies;
    }

    //create a new policy for a customer [case 3]
    public void createNewPolicy(String customerId, CustomerService customerService, CoverType coverType,
            Long coverValue, String duration, long installmentAmount, String paymentPlan, Date startDate) {
  
    	//It helps to find and connect between Policy and Customer.
    	//Search for customers based on customer ID help for
        Customer customer = customerService.findCustomerById(customerId);
        
        // If cannot find customer by customer id, return fail
        if (customer == null) {
            System.out.println("No customer with id " + customerId);
            return;
        } 

        //Store the ID of the new policy
        long policyId;
        
        if (this.policies.isEmpty()) { //Check if policies list is empty
            policyId = 101; //If array policy is empty, new policy id is 101
        } else {
        	
            // If array policy have some policies, new policy id is last policy id in array + 1
            Policy lastPolicy = policies.get(policies.size() - 1); //see last value in list
            policyId = lastPolicy.getPolicyId() + 1; //Create a new ID number for the policy
        }

        //All the information that the new policy needs to have
        Policy newPolicy = new Policy(policyId, customer.getCustomerId(), coverType, coverValue, duration,
                installmentAmount, paymentPlan, startDate);
        
        //Push it into policies array list
        this.policies.add(newPolicy);
        
        //Push it into customer policies array list 
        customer.getPolicies().add(newPolicy);
        return;
    }

    // Check if there are any policies in the class Policy [case 4]
    public void printAllPolicies() {
        System.out.println("List policies:");

        if (this.policies.isEmpty()) {
        	//Empty list
            System.out.println("Empty policies!!!");
            return;
        }

        for (Policy policy : policies) {
            System.out.println(policy.toString());
        }
    }

    //Search for a policy in the policies list by policy ID
    public Policy findPolicyById(String policyId) {
        for (Policy policy : policies) {
            if (policyId.equals("P" + policy.getPolicyId())) {
                return policy;
            }
        }

        return null;
    }

    // Method calculate total cover of all policy  [case 8]
    public long totalCoverValue() {
        long total = 0;
        for (Policy policy : policies) {
            total += policy.getCoverValue();
        }

        return total;
    }

    //Method calculate total installment amount of all policy [case 9]
    public long totalInstallmentAmount() {
        long total = 0;
        for (Policy policy : policies) {
            total += policy.getInstallmentAmount();
        }

        return total;
    }
}

/**
 * Main
 */
public class Question_B {
	// Max customer is 7
    private static final int MAX_CUSTOMER = 7;

    //case 1: Creation of new Customers 
    public static void createNewCustomer(Scanner input, CustomerService customerService) {
        System.out.print("Input name: ");
        String customerName = input.nextLine();
        System.out.print("Input address: ");
        String address = input.nextLine();
        customerService.createNewCustomer(customerName, address);
        return;
    }

    //case 2: print All Customer
    public static void printAllCustomer(Scanner input, CustomerService customerService) {
        System.out.print("Are you want to print policy too? ");
        String yonChoice = input.nextLine();

        if (yonChoice.equals("Y") || yonChoice.equals("y")) {
            customerService.printAllCustomer(true);
        } else if (yonChoice.equals("N") || yonChoice.equals("n")) {
            customerService.printAllCustomer(false);
        } else {
            System.out.println("No have choice " + yonChoice);
        }

        return;
    }

    //case 3:Creation of new Policy
    public static void createNewPolicy(Scanner input, CustomerService customerService, PolicyService policyService) {
        System.out.print("Input customer Id: ");
        String customerId = input.nextLine();
        System.out.print("Input cover type (Vehicle / Health / Travel / Property / Pet): ");
        String coverTypeStr = input.nextLine();
        CoverType coverType;
        
        //Helps handle exceptions, keeping the application running smoothly
        //Check if the 5 values ​​are entered correctly in CoverType
        try {
            coverType = CoverType.valueOf(coverTypeStr);
        } catch (Exception e) {
            System.out.println("Invalid cover type!!!");
            return;
        }
        System.out.print("Input cover value: ");
        long coverValue = input.nextLong();
        input.nextLine();

        System.out.print("Input duration: ");
        String duration = input.nextLine();

        System.out.print("Input installment amount: ");
        long installmentAmount = input.nextLong();
        input.nextLine();

        System.out.print("Input payment plan: ");
        String paymentPlan = input.nextLine();

        policyService.createNewPolicy(customerId, customerService, coverType, coverValue, duration, installmentAmount,
                paymentPlan, new Date());
        //new Date(): Get the date from the laptop system in use
        return;
    }

    //case 4:print All Policies
    public static void printAllPolicies(PolicyService policyService) {
        policyService.printAllPolicies();
        return;
    }

    //case 5: Search for customer information
    public static void searchCustomer(Scanner input, CustomerService customerService) {
        System.out.println("Search by: ");
        System.out.println("a. Customer id.");
        System.out.println("b. Customer name.");
        System.out.println("c. Policy id.");
        System.out.print("Input your option: ");
        char option = input.nextLine().charAt(0);

        if (option == 'a') {
            System.out.print("Input customer id: ");
            String customerId = input.nextLine();
            Customer customer = customerService.findCustomerById(customerId);
            if (customer == null) {
                System.out.println("No customer with id " + customerId);
                return;
            }
            System.out.println(customer.toString());
            return;

        } else if (option == 'b') {
            System.out.print("Input customer name: ");
            String customerName = input.nextLine();

            List<Customer> listCustomer = customerService.findCustomerByName(customerName);
            if (listCustomer.isEmpty()) {
                System.out.println("No customer with name " + customerName);
                return;
            }
            for (Customer customer : listCustomer) {
                System.out.println(customer.toString());
            }
            return;
        } else if (option == 'c') {
            System.out.print("Input policy id: ");
            String policyId = input.nextLine();
            Customer customer = customerService.findByPolicyId(policyId);
            if (customer == null) {
                System.out.println("No customer with  policy id " + policyId);
                return;
            }
            System.out.println(customer.toString());
            return;
        } else {
            System.out.println("No option " + option);
            return;
        }
    }

    //case 6: update Policy
    public static void updatePolicy(Scanner input, PolicyService policyService) {
        System.out.print("Input policy id to update: ");
        String policyId = input.nextLine();

        //search for Policy by Id and then store it in policy variable
        Policy policy = policyService.findPolicyById(policyId);

        if (policy == null) {
            System.out.println("No policy with id " + policyId);
            return;
        }

        //menu for update
        System.out.println("What do you want to update ?");
        System.out.println("a. Update cover type.");
        System.out.println("b. Update cover value.");
        System.out.println("c. Update cover duration.");
        System.out.println("d. Update cover installment amount.");
        System.out.println("f. Update cover payment plan.");
        System.out.print("Input your option: ");
        char option = input.nextLine().charAt(0);

        switch (option) {
            case 'a':
                System.out.print("Input cover type: ");
                System.out.print("Input cover type (Vehicle / Health / Travel / Property / Pet): ");
                String coverTypeStr = input.nextLine();
                CoverType coverType;
                try {
                    coverType = CoverType.valueOf(coverTypeStr);
                } catch (Exception e) {
                    System.out.println("Invalid cover type!!!");
                    break;
                }

                policy.setCoverType(coverType);
                break;
            case 'b':
                System.out.print("Input cover value: ");
                long coverValue = input.nextLong();
                input.nextLine();

                policy.setCoverValue(coverValue);
                break;
            case 'c':
                System.out.print("Input duration: ");
                String duration = input.nextLine();
                policy.setDuration(duration);
                break;
            case 'd':
                System.out.print("Input installment amount: ");
                long installmentAmount = input.nextLong();
                input.nextLine();

                policy.setInstallmentAmount(installmentAmount);
                break;
            case 'f':
                System.out.print("Input payment plan: ");
                String paymentPlan = input.nextLine();

                policy.setPaymentPlan(paymentPlan);
                break;
            default:
                break;
        }

        return;
    }

    //case 7: Function delete policy to change is active to false
    public static void deletePolicy(Scanner input, PolicyService policyService) {
        System.out.print("Input policy id to delete: ");
        String policyId = input.nextLine();

        //search for Policy by Id and then store it in policy variable
        Policy policy = policyService.findPolicyById(policyId);

        if (policy == null) {
            System.out.println("No policy with id " + policyId);
            return;
        }

        //disable policy
        policy.setActive(false);
        return;
    }

    //case 8: Function get totle cover value
    public static void totalCoverValue(PolicyService policyService) {
        System.out.println("Total cover value: " + policyService.totalCoverValue());
    }

    //case 9: Function get total installment amount
    public static void totalInstallmentAmount(PolicyService policyService) {
        System.out.println("Total amount value: " + policyService.totalInstallmentAmount());
    }

    public static void menu() {
        System.out.println("\n==================================");
        System.out.println("1. Create new Customer.");
        System.out.println("2. Print All Customer.");
        System.out.println("3. Create new Policy.");
        System.out.println("4. Print all Policy.");
        System.out.println("5. Search customer.");
        System.out.println("6. Update Policy.");
        System.out.println("7. Delete Policy.");
        System.out.println("8. Total Cover Value.");
        System.out.println("9. Total Installment Amount.");
        System.out.println("10. Exit.");
        System.out.print("Input your option: ");
    }

    public static void main(String[] args) {
        boolean isOpenMenu = true;
        // Create Customer Array List
        List<Customer> customers = new ArrayList<Customer>(MAX_CUSTOMER);
        CustomerService customerService = new CustomerService(customers);

        // Create Policy Array List
        List<Policy> policies = new ArrayList<Policy>();
        PolicyService policyService = new PolicyService(policies);

        Scanner input = new Scanner(System.in);

        while (isOpenMenu) {
            menu();
            int option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    createNewCustomer(input, customerService);
                    break;
                case 2:
                    printAllCustomer(input, customerService);
                    break;
                case 3:
                    createNewPolicy(input, customerService, policyService);
                    break;
                case 4:
                    printAllPolicies(policyService);
                    break;
                case 5:
                    searchCustomer(input, customerService);
                    break;
                case 6:
                    updatePolicy(input, policyService);
                    break;
                case 7:
                    deletePolicy(input, policyService);
                    break;
                case 8:
                    totalCoverValue(policyService);
                    break;
                case 9:
                    totalInstallmentAmount(policyService);
                    break;
                case 10:
                    isOpenMenu = false;
                    break;
                default:
                    break;
            }
        }

        input.close();
    }
}