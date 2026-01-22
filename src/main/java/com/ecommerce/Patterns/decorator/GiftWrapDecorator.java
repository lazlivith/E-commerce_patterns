package com.ecommerce.Patterns.decorator;

import com.ecommerce.Patterns.composite.ProductComponent;

public class GiftWrapDecorator implements ProductComponent {

    private final ProductComponent wrapped;

    public GiftWrapDecorator(ProductComponent product) {
        this.wrapped = product;
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice() + 5;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + emballage cadeau";
    }

    @Override
    public String getName() {
        return wrapped.getName();
    }

    public ProductComponent getWrapped() {
        return wrapped;
    }
}
