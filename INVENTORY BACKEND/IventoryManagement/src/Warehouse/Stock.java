package Warehouse;

import java.util.Date;
import java.util.List;

public class Stock extends Item {
    private int quantity;
    private String transactionType;
    private Date transactionDate;
    private DatabaseManager dbManager; // DatabaseManager reference

    public Stock(String itemName, int quantity, String transactionType, Date transactionDate, DatabaseManager dbManager) {
        super(itemName);
        this.quantity = quantity;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.dbManager = dbManager; // Set the DatabaseManager reference
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public void addItem() {
        dbManager.addStock(this);
        System.out.println("Stock added successfully!");
    }

    @Override
    public void viewItems() {
        List<Stock> stockTransactions = dbManager.getAllStockTransactions();

        System.out.println("All Stock Transactions:");
        for (Stock stock : stockTransactions) {
            System.out.println(stock);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Quantity: " + quantity + ", Transaction Type: " + transactionType +
                ", Transaction Date: " + transactionDate;
    }
}
