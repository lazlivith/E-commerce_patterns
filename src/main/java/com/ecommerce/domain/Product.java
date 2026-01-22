package com.ecommerce.domain;

import com.ecommerce.Patterns.composite.ProductComponent;

public class Product implements ProductComponent {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(int i, String casque, int i1) {
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return 0;
    }
}
