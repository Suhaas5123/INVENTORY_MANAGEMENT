package Inventory;

public class Items {

	private String productName;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	Items(String productName){
		this.productName=productName;
	}

}
