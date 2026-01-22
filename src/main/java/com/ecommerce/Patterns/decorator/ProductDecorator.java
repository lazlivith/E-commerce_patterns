package com.ecommerce.Patterns.decorator;

import com.ecommerce.Patterns.composite.ProductComponent;

public abstract class ProductDecorator implements ProductComponent {

    protected ProductComponent product;

    protected ProductDecorator(ProductComponent product) {
        this.product = product;
    }

    public ProductComponent getWrapped() {
        return product;
    }
}
