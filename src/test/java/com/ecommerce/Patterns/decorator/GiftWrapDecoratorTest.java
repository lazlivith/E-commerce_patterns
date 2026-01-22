package com.ecommerce.Patterns.decorator;

import com.ecommerce.Patterns.composite.ProductComponent;
import com.ecommerce.Patterns.composite.SingleProduct;
import com.ecommerce.Patterns.decorator.GiftWrapDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GiftWrapDecoratorTest {

    @Test
    void giftWrap_shouldIncreasePriceAndChangeDescription() {
        ProductComponent product = new SingleProduct("Livre", 20.0);
        ProductComponent wrappedProduct = new GiftWrapDecorator(product);

        assertEquals(25.0, wrappedProduct.getPrice(), 0.001);
        assertTrue(wrappedProduct.getDescription().contains("emballage cadeau"));
    }
}
