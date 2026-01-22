package com.ecommerce.Patterns.factory;

public class CreditCardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paiement par carte bancaire : " + amount + " €");
    }
}
