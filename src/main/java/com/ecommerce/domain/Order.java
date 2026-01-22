package com.ecommerce.domain;

import com.ecommerce.Patterns.composite.ProductComponent;
import com.ecommerce.Patterns.observer.Observer;
import com.ecommerce.Patterns.strategy.NormalPricing;
import com.ecommerce.Patterns.strategy.PricingStrategy;


import java.util.ArrayList;
import java.util.List;

public class Order {

    // Liste de produits (simples, packs ou décorés)
    private final List<ProductComponent> products = new ArrayList<>();

    // Strategy (par défaut : prix normal)
    private PricingStrategy pricingStrategy;

    // Observers (email, log, etc.)
    private final List<Observer> observers = new ArrayList<>();

    // 🔹 CONSTRUCTEUR
    public Order() {
        //  IMPORTANT : stratégie par défaut
        this.pricingStrategy = new NormalPricing();
    }

    // Gestion des produits
    public void addProduct(ProductComponent product) {
        products.add(product);
    }

    public void removeProduct(int index) {
        if (index >= 0 && index < products.size()) {
            ProductComponent removed = products.remove(index);
            notifyObservers("Produit supprimé du panier : " + removed.getDescription());
        }
    }

    public List<ProductComponent> getProducts() {
        return products;
    }

    // Strategy : prix
    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        if (pricingStrategy != null) {
            this.pricingStrategy = pricingStrategy;
        }
    }

    public double calculateTotal() {
        double baseTotal = products.stream()
                .mapToDouble(ProductComponent::getPrice)
                .sum();

        return pricingStrategy.calculate(baseTotal);
    }

    // Observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        observers.forEach(o -> o.update(message));
    }

    // Validation commande
    public void validate() {
        notifyObservers("Commande validée. Total = " + calculateTotal() + " €");
    }

    // Affichage panier
    public void showProducts() {
        System.out.println("\n🛒 Contenu de la commande :");

        if (products.isEmpty()) {
            System.out.println("❌ Panier vide");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            ProductComponent p = products.get(i);
            System.out.println((i + 1) + ". " + p.getDescription() + " : " + p.getPrice() + " €");
        }

        System.out.println("➡ Total actuel : " + calculateTotal() + " €");
    }
}
