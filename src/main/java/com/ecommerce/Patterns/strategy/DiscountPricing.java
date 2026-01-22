package com.ecommerce.Patterns.strategy;


public class DiscountPricing implements PricingStrategy {

    @Override
    public double calculate(double basePrice) {
        return basePrice * 0.9;
    }
}
