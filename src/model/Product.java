package model;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private double price;
    private String description;
    private ProductCategory productCategory;

    public Product(String newNameProduct, String newPrice, String newDes, String newProductCategory) {
        name = newNameProduct;
        this.price = Double.parseDouble(newPrice);
    }

    public Product(int id, String name, double price, String description,ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productCategory = productCategory;
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
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", productCategory= " +productCategory +"\n"+
                '}';
    }



}