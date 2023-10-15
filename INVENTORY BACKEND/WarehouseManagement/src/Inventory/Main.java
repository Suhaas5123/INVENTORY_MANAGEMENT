package Inventory;
import java.util.List;
import java.util.Scanner;
import java.sql.*;
import java.util.Date;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory?createDatabaseIfNotExist=true";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "suhaas";

    private static final Scanner scanner = new Scanner(System.in);
    private static final DatabaseManager dbManager = new DatabaseManager(DB_URL, DB_USERNAME, DB_PASSWORD);

    public static void main(String[] args) throws ClassNotFoundException {
        dbManager.createTables(); // Create tables if they don't exist
        dbManager.createDatabaseIfNotExists();
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Welcome to the Inventory Management System!");

        while (true) {
        	System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Add Incoming Stock");
            System.out.println("4. Add Outgoing Stock");
            System.out.println("5. View Stock Transactions");
            System.out.println("6. Update Product");
            System.out.println("7. Delete Product");
            System.out.println("8. Update Stock");
            System.out.println("9. Delete Stock");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
            case 1:
                addProduct();
                break;
            case 2:
                viewAllProducts();
                break;
            case 3:
                addIncomingStock();
                break;
            case 4:
                addOutgoingStock();
                break;
            case 5:
                viewStockTransactions();
                break;
            case 6:
                updateProduct();
                break;
            case 7:
                deleteProduct();
                break;
            case 8:
                updateStock();
                break;
            case 9:
                deleteStock();
                break;
            case 10:
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        }
    }

    private static void addProduct() {
        scanner.nextLine(); // Consume the newline character after reading the integer
        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();
        
        System.out.print("Enter the product price: ");
        int productPrice = scanner.nextInt();

        Product product = new Product(productName);
        product.setProductPrice(productPrice);
        dbManager.addProduct(product);

        System.out.println("Product added successfully!");
    }

    private static void viewAllProducts() {
        List<Product> products = dbManager.getAllProducts();

        System.out.println("All Products:");
        for (Product product : products) {
            System.out.println(product.getProductId() + " - " + product.getProductName()+ " - "+ product.getProductPrice());
        }
    }

    private static void addIncomingStock() {
        System.out.print("Enter the product ID: ");
        int productId = scanner.nextInt();

        System.out.print("Enter the quantity of incoming stock: ");
        int quantity = scanner.nextInt();

        Stock stock = new Stock(productId, quantity, "Incoming", new Date());
        dbManager.addStock(stock);

        System.out.println("Incoming stock added successfully!");
    }

    private static void addOutgoingStock() {
        System.out.print("Enter the product ID: ");
        int productId = scanner.nextInt();

        System.out.print("Enter the quantity of outgoing stock: ");
        int quantity = scanner.nextInt();

        Stock stock = new Stock(productId, quantity, "Outgoing", new Date());
        dbManager.addStock(stock);

        System.out.println("Outgoing stock added successfully!");
    }

    private static void viewStockTransactions() {
        List<Stock> stockTransactions = dbManager.getAllStockTransactions();

        System.out.println("All Stock Transactions:");
        for (Stock stock : stockTransactions) {
            System.out.println(stock.getStockId() + " - Product ID: " + stock.getProductId() + " - Product Name: " + stock.getProductName() +
                    " - Quantity: " + stock.getQuantity() + " - Transaction Type: " + stock.getTransactionType() +
                    " - Transaction Date: " + stock.getTransactionDate());
        }
    }
    private static void updateProduct() {
        System.out.print("Enter the product ID to update: ");
        int productId = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character after reading the integer
        System.out.print("Enter the new product name: ");
        String productName = scanner.nextLine();
        // Consume the newline character after reading the integer
        System.out.print("Enter the new product price: ");
        int productPrice = scanner.nextInt();

        Product product = new Product(productName);
        product.setProductId(productId);
        product.setProductPrice(productPrice);
        dbManager.updateProduct(product);

        System.out.println("Product updated successfully!");
    }

    private static void deleteProduct() {
        System.out.print("Enter the product ID to delete: ");
        int productId = scanner.nextInt();

        dbManager.deleteProduct(productId);

        System.out.println("Product deleted successfully!");
    }

    private static void updateStock() {
        System.out.print("Enter the stock ID to update: ");
        int stockId = scanner.nextInt();

        System.out.print("Enter the new product ID: ");
        int productId = scanner.nextInt();

        System.out.print("Enter the new quantity: ");
        int quantity = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character after reading the integer
        System.out.print("Enter the new transaction type (Incoming/Outgoing): ");
        String transactionType = scanner.nextLine();

        Stock stock = new Stock(productId, quantity, transactionType, new Date());
        stock.setStockId(stockId);
        dbManager.updateStock(stock);

        System.out.println("Stock updated successfully!");
    }

    private static void deleteStock() {
        System.out.print("Enter the stock ID to delete: ");
        int stockId = scanner.nextInt();

        dbManager.deleteStock(stockId);

        System.out.println("Stock deleted successfully!");
    }
}
