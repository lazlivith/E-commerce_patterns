package com.ecommerce.Patterns.strategy;

import com.ecommerce.Patterns.strategy.PricingStrategy;

public class NormalPricing implements PricingStrategy {

    @Override
    public double calculate(double basePrice) {
        return basePrice;
    }
}
