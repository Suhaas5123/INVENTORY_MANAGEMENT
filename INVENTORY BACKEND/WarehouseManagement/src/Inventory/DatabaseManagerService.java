package Inventory;

import java.util.List;

public interface DatabaseManagerService {
	
	void createDatabaseIfNotExists();
	void createTables();
	void addProduct(Product product);
	List<Product> getAllProducts() ;
	void addStock(Stock stock);
	List<Stock> getAllStockTransactions() ;
	void updateProduct(Product product);
	void deleteProduct(int productId);
	void updateStock(Stock stock);
	void deleteStock(int stockId);
	

}
