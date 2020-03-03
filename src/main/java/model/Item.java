package model;

public class Item {
    private String item;
    private double price;
    private int quantity;
    private double amount;

    public Item(String item, double price, int quantity, double amount) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
