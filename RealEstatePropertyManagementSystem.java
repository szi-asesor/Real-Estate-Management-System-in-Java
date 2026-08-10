package realestatepropertymanagementsystem;

import java.util.Scanner;

public class RealEstatePropertyManagementSystem {
    
    //property info
    static String[] propertyID = new String[50];
    static String[] propertyLocation = new String[50];
    static double[] propertyPrice = new double[50];
    static boolean[] isSold = new boolean[50];
    static int propertyCount = 0;

    //buyer info
    static String[] buyerName = new String[50];
    static String[] buyerContact = new String[50];
    static double[] buyerBudget = new double[50];
    static int buyerCount = 0;

    //sales info
    static String[] soldProperty = new String[50];
    static String[] soldTo = new String[50];
    static double[] salePrice = new double[50];
    static int saleCount = 0;

    static Scanner userInput = new Scanner(System.in);
    
    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            System.out.print("Enter choice: ");
            choice = userInput.nextInt();
            userInput.nextLine();

            switch (choice) {
                case 1 -> addProperty();
                case 2 -> addBuyer();
                case 3 -> recordSale();
                case 4 -> viewSales();
                case 5 -> System.out.println("Thank you for using our system!");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);
    }

    // main menu 
    static void showMenu() {
        System.out.println("\n==========================================");
        System.out.println("# REAL ESTATE PROPERTY MANAGEMENT SYSTEM #");
        System.out.println("==========================================");
        System.out.println("1. Add a Property");
        System.out.println("2. Add a Buyer");
        System.out.println("3. Record a Sale");
        System.out.println("4. View All Sales");
        System.out.println("5. Exit\n");
    }

    // add new property 
    static void addProperty() {
        System.out.println("\n--- Add Property ---");
        if (propertyCount < propertyID.length) {
            System.out.print("Enter Property Name/ID: ");
            propertyID[propertyCount] = userInput.nextLine();

            System.out.print("Enter Location: ");
            propertyLocation[propertyCount] = userInput.nextLine();

            System.out.print("Enter Price: ");
            propertyPrice[propertyCount] = userInput.nextDouble();

            isSold[propertyCount] = false;
            propertyCount++;

            System.out.println("Property added successfully!");
        } else System.out.println("Property list is full!");
    }

    // add a new buyer
    static void addBuyer() {
        System.out.println("\n--- Add Buyer ---");
        if (buyerCount < buyerName.length) {
            System.out.print("Enter Buyer Name: ");
            buyerName[buyerCount] = userInput.nextLine();

            System.out.print("Enter Contact Number: ");
            buyerContact[buyerCount] = userInput.nextLine();

            System.out.print("Enter Budget: ");
            buyerBudget[buyerCount] = userInput.nextDouble();

            buyerCount++;
            System.out.println("Buyer added successfully!");
        } else System.out.println("Buyer list is full!");
    }
    
    // record a sale 
    static void recordSale() {
        System.out.println("\n--- Available Properties ---");
        for (int i = 0; i < propertyCount; i++) {
            if (!isSold[i]) {
                System.out.println((i + 1) + ". " + propertyID[i] + " | " + propertyLocation[i] + " | ₱" + propertyPrice[i]);
            }
        }

        System.out.print("Select property number to sell: ");
        int a = userInput.nextInt() - 1;

        if (a < 0 || a >= propertyCount || isSold[a]) {
            System.out.println("Invalid property number!");
            return;
        }

        System.out.println("\n--- Buyers ---");
        for (int i = 0; i < buyerCount; i++) {
            System.out.println((i + 1) + ". " + buyerName[i] + " | Budget: ₱" + buyerBudget[i]);
        }

        System.out.print("Select buyer number: ");
        int b = userInput.nextInt() - 1;

        if (b < 0 || b >= buyerCount) {
            System.out.println("Invalid buyer number!");
            return;
        }

        // checks if buyer can afford
        if (buyerBudget[b] >= propertyPrice[a]) {
            // subtract price from buyer's budget
            buyerBudget[b] -= propertyPrice[a]; 
            
            // marks as sold and record sale
            isSold[a] = true;
            soldProperty[saleCount] = propertyID[a];
            soldTo[saleCount] = buyerName[b];
            salePrice[saleCount] = propertyPrice[a];
            saleCount++;
            
            System.out.println("Sale is recorded successfully!");
            // display buyer's new budget
            System.out.println("Buyer " + buyerName[b] + "'s new budget is: ₱" + buyerBudget[b]);
        } else System.out.println("The buyer cannot afford this property.");
    }

    // view all sales 
    static void viewSales() {
        if (saleCount == 0) System.out.println("No recorded sales yet.");
        else {
            System.out.println("\n--- Completed Sales ---");
            for (int i = 0; i < saleCount; i++) {
                System.out.println((i + 1) + ". Property Name/ID: " + soldProperty[i] + " | Buyer: " + soldTo[i] + " | Price: ₱" + salePrice[i]);
            }
        }
    }
}