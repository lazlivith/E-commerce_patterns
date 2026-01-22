package com.ecommerce.Patterns.factory;

public class PayPalPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paiement via PayPal : " + amount + " €");
    }
}
