import java.util.*; 
abstract class OnlineOrder { 
	private static int orderNo1 = 1;
	private int orderNo;  	
	private double amount; 

	public OnlineOrder(double amount) { 
		this.orderNo=orderNo1++; 
		this.amount=amount; 
	} 

	public int getOrderNo() { 
		return this.orderNo; 
	} 
	public double getAmount() {
		return amount;
	}
	public String toString() { 
		return "Order No: "+orderNo+" Amount: "+amount; 
	} 

	abstract void DeliveryMethod();
} 

class ClickAndCollect extends OnlineOrder {
	private String Customer_Name;
	private String Store_Name;
	private String Store_Address;

	public ClickAndCollect(double amount, String Customer_Name, String Store_Name, String Store_Address) {
		super(amount);
		this.Customer_Name = Customer_Name;
		this.Store_Name = Store_Name;
		this.Store_Address = Store_Address;
	}

	public String getCustomerName() {
		return Customer_Name;
	}

	public String getStoreName() {
		return Store_Name;
	}
	public String getStoreAddress() {
		return Store_Address;
	}

	void DeliveryMethod() {
		System.out.println("Order collected in store by Click & Collect");
	}
	public String toString() {
		return super.toString() + ", Customer Name: " + Customer_Name + ", Store Name: " + Store_Name + ", Store Address: " + Store_Address;
	}
}

class Delivery extends OnlineOrder {
	private String Customer_Name1;
	private String Customer_Address1;
	private String Delvery_Type;

	public Delivery(double amount, String Customer_Name1, String Customer_Address1, String Delvery_Type) {
		super(amount);
		this.Customer_Name1 = Customer_Name1;
		this.Customer_Address1 = Customer_Address1;
		this.Delvery_Type = Delvery_Type;
	}

	public String getCustomerName() {
		return Customer_Name1;
	}
	public String getCustomerAddress() {
		return Customer_Address1;
	}
	public String getDeliveryType() {
		return Delvery_Type;
	}


	void DeliveryMethod() {
		System.out.println("Order delivered to customer address");
	}
	public String toString() {
		return super.toString() + ", Customer Name: " + Customer_Name1 + ", Customer Address: " + Customer_Address1 + ", Delivery Type: " + Delvery_Type;
	}
}

public class OnlineOrder_Demo {  
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        ArrayList<OnlineOrder> collection = new ArrayList<OnlineOrder>(); 
        int option = 0; 

        do {
            System.out.println("\n1 - New Order"); 
            System.out.println("2 - Display All Orders");         
            System.out.println("3 - Search and print order");
            System.out.println("4 - Delete an order");
            System.out.println("5 - Maximum order");
            System.out.println("6 - Exit");
            option = sc.nextInt();  

            switch(option) {  
                case 1: 
                    createNewOrder(sc, collection);
                    break;  
                case 2: 
                    displayAllOrders(collection);
                    break; 
                case 3: 
                    searchAndPrintOrder(sc, collection);
                    break;
                case 4:
                    deleteOrder(sc, collection);
                    break;
                case 5:
                    findMaxOrder(collection);
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while(option != 6); 

        sc.close();
    }

    private static void createNewOrder(Scanner sc, ArrayList<OnlineOrder> collection) {
        System.out.print("Enter Order Amount: ");  
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("\nCustomer name: ");
        String custName = sc.nextLine();

        int type;
        do {
			System.out.print("Enter Order Type: "
					+ "\n1 - Click & Collec "
					+ "\n2 - Delivery: ");            
			type = sc.nextInt();
            sc.nextLine();
        } while (type != 1 && type != 2);

        switch (type) {
            case 1: 
                createClickAndCollectOrder(sc, collection, amount, custName);
                break;
            case 2: 
                createDeliveryOrder(sc, collection, amount, custName);
                break;
        }
    }

    private static void createClickAndCollectOrder(Scanner sc, ArrayList<OnlineOrder> collection, double amount, String custName) {
        System.out.print("\nStore Name: ");
        String storeName = sc.nextLine();
        System.out.print("Store Address: ");
        String storeAddress = sc.nextLine();

        collection.add(new ClickAndCollect(amount, custName, storeName, storeAddress));
        System.out.println("Click & Collect order created successfully.");
    }

    private static void createDeliveryOrder(Scanner sc, ArrayList<OnlineOrder> collection, double amount, String custName) {
        System.out.print("\nCustomer Address: ");
        String customerAddr = sc.nextLine();

        String deliveryType;
        do {
            System.out.print("\nDelivery type (Normal/Express): ");
            deliveryType = sc.nextLine();
        } while (!deliveryType.equals("Normal") && !deliveryType.equals("Express"));

        collection.add(new Delivery(amount, custName, customerAddr, deliveryType));
        System.out.println("Delivery order created successfully.");
    }

    private static void displayAllOrders(ArrayList<OnlineOrder> collection) {
        if (collection.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }
        
        for (OnlineOrder order : collection) {
            System.out.println(order.toString());
            order.DeliveryMethod();
        }
    }

    private static void searchAndPrintOrder(Scanner sc, ArrayList<OnlineOrder> collection) {
        if (collection.isEmpty()) {
            System.out.println("Order Collection is Empty");
            return;
        }
        
        System.out.print("Enter Order No: ");
        int toSearch = sc.nextInt();

        for (OnlineOrder order : collection) {
            if (order.getOrderNo() == toSearch) {
                System.out.println(order.toString());
                order.DeliveryMethod();
                return;
            }
        }

        System.out.println("Order not found");
    }

    private static void deleteOrder(Scanner sc, ArrayList<OnlineOrder> collection) {
        if (collection.isEmpty()) {
            System.out.println("Order Collection is Empty");
            return;
        }

        System.out.print("Enter Order No: ");
        int toDelete = sc.nextInt();

        Iterator<OnlineOrder> iterator = collection.iterator();
        while (iterator.hasNext()) {
            OnlineOrder order = iterator.next();
            if (order.getOrderNo() == toDelete) {
                iterator.remove();
                System.out.println("Order deleted successfully");
                return;
            }
        }

        System.out.println("Order not found");
    }

    private static void findMaxOrder(ArrayList<OnlineOrder> collection) {
        if (collection.isEmpty()) {
            System.out.println("Order Collection is Empty");
            return;
        }

        OnlineOrder highest = Collections.max(collection, Comparator.comparing(OnlineOrder::getAmount));
        System.out.println("Order with the maximum amount:");
        System.out.println(highest.toString());
        highest.DeliveryMethod();
    }
}
