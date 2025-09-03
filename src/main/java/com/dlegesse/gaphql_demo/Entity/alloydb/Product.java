package com.dlegesse.gaphql_demo.Entity.alloydb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products") // Example table for AlloyDB
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private double price;

    public Product() {}

    public Product(Integer id, String productName, double price) {
        this.productName = productName;
        this.price = price;
        this.id = id;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
