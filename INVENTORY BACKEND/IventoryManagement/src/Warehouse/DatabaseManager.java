package Warehouse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public void createDatabaseIfNotExists() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS inventory";
            PreparedStatement createDatabaseStatement = conn.prepareStatement(createDatabaseQuery);
            createDatabaseStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTables() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String createProductTableQuery = "CREATE TABLE IF NOT EXISTS products ("
                    + "productId INT AUTO_INCREMENT PRIMARY KEY, "
                    + "productName VARCHAR(255) NOT NULL, "
                    + "productPrice INT)";
            PreparedStatement createProductTableStatement = conn.prepareStatement(createProductTableQuery);
            createProductTableStatement.executeUpdate();

            String createStockTableQuery = "CREATE TABLE IF NOT EXISTS stock ("
                    + "stockId INT AUTO_INCREMENT PRIMARY KEY, "
                    + "productId INT NOT NULL, "
                    + "quantity INT NOT NULL, "
                    + "transactionType VARCHAR(10) NOT NULL, "
                    + "transactionDate DATE NOT NULL)";
            PreparedStatement createStockTableStatement = conn.prepareStatement(createStockTableQuery);
            createStockTableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String insertQuery = "INSERT INTO products (productName,productPrice) VALUES (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getProductPrice());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setProductId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM products";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                int productPrice =resultSet.getInt("productPrice");
                Product product = new Product(productName);
                product.setProductPrice(productPrice);
                product.setProductId(productId);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void addStock(Stock stock) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String insertQuery = "INSERT INTO stock (productId, quantity, transactionType, transactionDate) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, stock.getProductId());
            preparedStatement.setInt(2, stock.getQuantity());
            preparedStatement.setString(3, stock.getTransactionType());
            preparedStatement.setDate(4, new java.sql.Date(stock.getTransactionDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                stock.setStockId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Stock> getAllStockTransactions() {
        List<Stock> stockTransactions = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT s.*,p.productName FROM stock s join products p on p.productId=s.productId";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int stockId = resultSet.getInt("stockId");
                int productId = resultSet.getInt("productId");
                String productName =resultSet.getString("productName");
                int quantity = resultSet.getInt("quantity");
                String transactionType = resultSet.getString("transactionType");
                Date transactionDate = resultSet.getDate("transactionDate");
                Stock stock = new Stock(productId, quantity, transactionType, transactionDate);
                stock.setStockId(stockId);
                stock.setProductName(productName);
                stockTransactions.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockTransactions;
    }

    // Add other methods for updating and deleting products and stock
    public void updateProduct(Product product) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE products SET productName=? WHERE productId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getProductId());
            preparedStatement.setInt(3, product.getProductPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM products WHERE productId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStock(Stock stock) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE stock SET productId=?, quantity=?, transactionType=?, transactionDate=? WHERE stockId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, stock.getProductId());
            preparedStatement.setInt(2, stock.getQuantity());
            preparedStatement.setString(3, stock.getTransactionType());
            preparedStatement.setDate(4, new java.sql.Date(stock.getTransactionDate().getTime()));
            preparedStatement.setInt(5, stock.getStockId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStock(int stockId) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM stock WHERE stockId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, stockId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
