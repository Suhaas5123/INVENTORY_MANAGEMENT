package Warehouse;
public abstract class Item {
    private int itemId;
    private String itemName;

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId + ", Item Name: " + itemName;
    }

    public abstract void addItem();
    public abstract void viewItems();
}
