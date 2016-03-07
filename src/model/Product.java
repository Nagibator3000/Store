package model;

public class Product {
    public long id;
    public String name;
    public double price;

    public Product(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {

    }
}
