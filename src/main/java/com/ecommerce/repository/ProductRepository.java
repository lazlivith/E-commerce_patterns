package com.ecommerce.repository;

import com.ecommerce.Patterns.composite.ProductComponent;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<ProductComponent> products = new ArrayList<>();

    public void save(ProductComponent product) {
        products.add(product);
    }

    public List<ProductComponent> findAll() {
        return new ArrayList<>(products);
    }

    public ProductComponent findByIndex(int index) {
        if (index < 0 || index >= products.size()) return null;
        return products.get(index);
    }

    public void remove(int index) {
        if (index >= 0 && index < products.size()) products.remove(index);
    }
}
