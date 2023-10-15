package Inventory;

import java.util.Date;

public class Stock {
    private int stockId;
    private int productId;
    private String productName;
    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	private int quantity;
    private String transactionType; // "Incoming" or "Outgoing"
    private Date transactionDate;

    public Stock(int productId, int quantity, String transactionType, Date transactionDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
}
