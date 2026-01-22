package com.ecommerce.Patterns.factory;

public class PaymentFactory {

    public static Payment createPayment(String type) {
        switch (type.toLowerCase()) {
            case "card":
                return new CreditCardPayment();
            case "paypal":
                return new PayPalPayment();
            default:
                throw new IllegalArgumentException("Type de paiement inconnu");
        }
    }
}
