package com.ecommerce.Patterns.strategy;

import com.ecommerce.Patterns.strategy.DiscountPricing;
import com.ecommerce.Patterns.strategy.NormalPricing;
import com.ecommerce.Patterns.strategy.PricingStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingStrategyTest {

    @Test
    void normalPricing_shouldReturnSamePrice() {
        PricingStrategy strategy = new NormalPricing();
        assertEquals(100.0, strategy.calculate(100.0));
    }

    @Test
    void discountPricing_shouldApply10PercentDiscount() {
        PricingStrategy strategy = new DiscountPricing();
        assertEquals(90.0, strategy.calculate(100.0));
    }
}
