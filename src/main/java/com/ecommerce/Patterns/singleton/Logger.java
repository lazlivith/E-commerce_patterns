package com.ecommerce.Patterns.singleton;

public class Logger {

    // Instance unique (privée et statique)
    private static Logger instance;

    private Logger() {
    }

    // Point d'accès global
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Méthode métier
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}










