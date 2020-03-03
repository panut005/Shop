package model;

public class Product {

    private String id;
    private String name;
    private double price;
    private String prices;
    private int quantity;
    private String quantitys;
    private String urlImage;

    public Product(String id,String name, double price, int quantity,String urlImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.urlImage = urlImage;
    }
    public Product(String id,String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(String id,String name, String price,String quantity,String urlImage) {
        this.id = id;
        this.name = name;
        this.prices =price;
        this.quantitys = quantity;
        this.urlImage = urlImage;
    }

    public Product(String id,String name, String price,String quantity) {
        this.id = id;
        this.name = name;
        this.prices =price;
        this.quantitys = quantity;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getQuantitys() {
        return quantitys;
    }

    public void setQuantitys(String quantitys) {
        this.quantitys = quantitys;
    }

    public String getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
