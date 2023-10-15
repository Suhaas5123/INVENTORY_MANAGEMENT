package Warehouse;

import java.util.List;

public class Product extends Item {
    private double productPrice;
    private DatabaseManager dbManager; // DatabaseManager reference

    public Product(String productName, DatabaseManager dbManager) {
        super(productName);
        this.dbManager = dbManager; // Set the DatabaseManager reference
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public void addItem() {
        dbManager.addProduct(this);
        System.out.println("Product added successfully!");
    }

    @Override
    public void viewItems() {
        List<Product> products = dbManager.getAllProducts();

        System.out.println("All Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Product Price: " + productPrice;
    }
}
