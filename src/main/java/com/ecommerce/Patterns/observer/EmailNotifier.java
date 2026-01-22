package com.ecommerce.Patterns.observer;


public class EmailNotifier implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Email envoyé : " + message);
    }
}
