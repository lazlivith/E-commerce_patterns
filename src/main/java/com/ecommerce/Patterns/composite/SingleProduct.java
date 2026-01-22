package com.ecommerce.Patterns.composite;

import com.ecommerce.Patterns.composite.ProductComponent; // bien importer la seule interface

public class SingleProduct implements ProductComponent {

    private final String name;
    private final double price;

    public SingleProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

}
