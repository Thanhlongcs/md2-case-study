package model;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private double price;
    private String description;
    private ProductCategory productCategory;
    private int amount;


    public Product(int id, String name, double price, String description,ProductCategory productCategory, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productCategory = productCategory;
        this.amount=amount;
    }

    public Product(int newAmount) {
        amount = newAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return
                "========" + id +
                "========" + name +
                "=======" + price +
                "========" + description +
                "========" + productCategory +
                "========" + amount +'\n';
    }
}