package com.ecommerce.service;

import com.ecommerce.Patterns.factory.Payment;
import com.ecommerce.domain.Order;
import com.ecommerce.domain.User;
import com.ecommerce.Patterns.factory.PaymentFactory;
import com.ecommerce.Patterns.singleton.Logger;
import com.ecommerce.repository.OrderRepository;

public class OrderService {

    private final OrderRepository orderRepository = new OrderRepository();

    /**
     * Traite la commande en appliquant le paiement et la validation,
     * puis enregistre la commande et affiche les logs avec le nom du client.
     *
     * @param order       La commande à traiter
     * @param paymentType Type de paiement ("PAYPAL" ou "CARD")
     * @param client      Le client qui passe la commande
     */
    public void processOrder(Order order, String paymentType, User client) {

        Logger logger = Logger.getInstance();

        // Affichage du nom du client
        logger.log("Nom du client : " + client.getName());
        logger.log("Traitement de la commande");

        // Création et exécution du paiement
        Payment payment = PaymentFactory.createPayment(paymentType);
        payment.pay(order.calculateTotal());

        // Validation et sauvegarde de la commande
        order.validate();
        orderRepository.save(order);

        // Log et notification de la commande
        logger.log("Email envoyé : Commande validée pour " + client.getName() +
                ". Total = " + order.calculateTotal() + " €");
        logger.log("Commande enregistrée");
    }
}
