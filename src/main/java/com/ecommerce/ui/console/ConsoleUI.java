package com.ecommerce.ui.console;

import com.ecommerce.Patterns.composite.ProductPack;
import com.ecommerce.Patterns.composite.SingleProduct;
import com.ecommerce.Patterns.composite.ProductComponent;
import com.ecommerce.Patterns.decorator.GiftWrapDecorator;
import com.ecommerce.Patterns.observer.EmailNotifier;
import com.ecommerce.Patterns.singleton.Logger;
import com.ecommerce.Patterns.strategy.PricingStrategy;
import com.ecommerce.Patterns.strategy.NormalPricing;
import com.ecommerce.Patterns.strategy.DiscountPricing;
import com.ecommerce.domain.Order;
import com.ecommerce.domain.Role;
import com.ecommerce.domain.User;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.OrderService;

import java.util.List;

public class ConsoleUI {

    private static final ProductRepository productRepository = new ProductRepository();
    private final java.util.Scanner scanner = new java.util.Scanner(System.in);
    private final Logger logger = Logger.getInstance();
    private final OrderService orderService = new OrderService();

    public ConsoleUI(ProductRepository productRepository, OrderRepository orderRepository) {
    }

    // POINT D’ENTRÉE UI
    public void start() {
        logger.log("Démarrage de l'interface console");

        boolean running = true;
        while (running) {
            User currentUser = selectUser();

            if (currentUser.isAdmin()) {
                adminMenu();
            } else {
                clientMenu(currentUser);
            }

            System.out.print("\n🔁 Revenir au menu principal ? (O/N) : ");
            String again = scanner.nextLine().trim().toUpperCase();
            if (!again.equals("O")) running = false;
        }

        logger.log("Fin de l'application");
    }

    // SÉLECTION UTILISATEUR
    private User selectUser() {
        int choice = readInt("Sélectionnez le type d'utilisateur :\n1. Admin\n2. Client\nVotre choix : ");

        System.out.print("Nom d'utilisateur : ");
        String name = scanner.nextLine();

        return (choice == 1) ? new User(name, Role.ADMIN) : new User(name, Role.CLIENT);
    }

    // MENU ADMIN
    private void adminMenu() {
        while (true) {
            System.out.println("\n📦 MENU ADMIN");
            System.out.println("1. Créer un produit");
            System.out.println("2. Créer un pack");
            System.out.println("3. Supprimer un produit / pack");
            System.out.println("0. Retour");

            int choice = readInt("Choix : ");

            switch (choice) {
                case 0 -> { return; }
                case 1 -> createProduct();
                case 2 -> createPack();
                case 3 -> removeProduct();
                default -> System.out.println("❌ Choix invalide");
            }
        }
    }

    private void createProduct() {
        System.out.print("Nom du produit : ");
        String name = scanner.nextLine();

        double price = readDouble("Prix : ");

        SingleProduct product = new SingleProduct(name, price);
        productRepository.save(product);

        System.out.println("✔ Produit créé : " + product.getDescription());
    }

    private void createPack() {
        System.out.print("Nom du pack : ");
        String name = scanner.nextLine();

        ProductPack pack = new ProductPack(name);

        while (true) {
            List<SingleProduct> products = productRepository.findAll().stream()
                    .filter(p -> p instanceof SingleProduct)
                    .map(p -> (SingleProduct) p)
                    .toList();

            if (products.isEmpty()) {
                System.out.println("❌ Aucun produit disponible");
                break;
            }

            System.out.println("Produits disponibles :");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).getDescription());
            }

            int choice = readInt("Numéro du produit (0 pour finir) : ");

            if (choice == 0) break;
            if (choice < 1 || choice > products.size()) {
                System.out.println("❌ Choix invalide");
                continue;
            }

            pack.addComponent(products.get(choice - 1));
        }

        ProductComponent finalPack = askGiftWrap()
                ? new GiftWrapDecorator(pack)
                : pack;

        productRepository.save(finalPack);
        System.out.println("✔ Pack créé : " + finalPack.getDescription());
    }

    private void removeProduct() {
        List<ProductComponent> products = productRepository.findAll();
        if (products.isEmpty()) {
            System.out.println("❌ Aucun produit à supprimer");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getDescription());
        }

        int index = readInt("Numéro à supprimer : ") - 1;

        if (index >= 0 && index < products.size()) {
            System.out.println("✔ Supprimé : " + products.remove(index).getDescription());
        } else {
            System.out.println("❌ Numéro invalide");
        }
    }

    // MENU CLIENT
    private void clientMenu(User user) {
        Order order = new Order();
        order.addObserver(new EmailNotifier());

        while (true) {
            System.out.println("\n🛒 MENU CLIENT");
            System.out.println("1. Ajouter produit");
            System.out.println("2. Ajouter pack");
            System.out.println("3. Voir panier");
            System.out.println("4. Payer");
            System.out.println("0. Retour");

            int choice = readInt("Choix : ");

            switch (choice) {
                case 0 -> { return; }
                case 1 -> chooseProduct(order);
                case 2 -> choosePack(order);
                case 3 -> order.showProducts();
                case 4 -> handlePayment(order, user);
                default -> System.out.println("❌ Choix invalide");
            }
        }
    }

    private void chooseProduct(Order order) {
        List<SingleProduct> products = productRepository.findAll().stream()
                .filter(p -> p instanceof SingleProduct)
                .map(p -> (SingleProduct) p)
                .toList();

        if (products.isEmpty()) {
            System.out.println("❌ Aucun produit disponible");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getDescription());
        }

        int index = readInt("Choix : ") - 1;

        if (index < 0 || index >= products.size()) return;

        ProductComponent p = products.get(index);
        if (askGiftWrap()) p = new GiftWrapDecorator(p);

        order.addProduct(p);
        System.out.println("✔ Ajouté au panier");
    }

    private void choosePack(Order order) {
        List<ProductComponent> packs = productRepository.findAll().stream()
                .filter(p -> unwrapPack(p) != null)
                .toList();

        if (packs.isEmpty()) {
            System.out.println("❌ Aucun pack disponible");
            return;
        }

        for (int i = 0; i < packs.size(); i++) {
            System.out.println((i + 1) + ". " + packs.get(i).getDescription());
        }

        int index = readInt("Choix : ") - 1;

        if (index < 0 || index >= packs.size()) return;

        ProductComponent pack = packs.get(index);
        if (askGiftWrap()) pack = new GiftWrapDecorator(pack);

        order.addProduct(pack);
        System.out.println("✔ Pack ajouté");
    }

    private void handlePayment(Order order, User user) {
        if (order.getProducts().isEmpty()) {
            System.out.println("❌ Votre panier est vide, ajoutez un article avant de payer.");
            return;
        }

        System.out.println("\nNom du client : " + user.getName());

        // Choix stratégie de prix
        System.out.println("💰 Choisissez une stratégie de prix :");
        System.out.println("1. Prix normal");
        System.out.println("2. Prix avec réduction (10%)");

        int pricingChoice = readInt("Votre choix : ");
        PricingStrategy strategy =
                (pricingChoice == 2) ? new DiscountPricing() : new NormalPricing();
        order.setPricingStrategy(strategy);

        // Choix moyen de paiement
        System.out.println("\n💳 Choisissez un moyen de paiement :");
        System.out.println("1. PayPal");
        System.out.println("2. Carte bancaire");

        int paymentChoice = readInt("Votre choix : ");
        String paymentType = (paymentChoice == 2) ? "CARD" : "PAYPAL";

        orderService.processOrder(order, paymentType, user);
    }

    // UTILITAIRES
    private boolean askGiftWrap() {
        System.out.print("Emballage cadeau ? (O/N) : ");
        return scanner.nextLine().trim().equalsIgnoreCase("O");
    }

    private ProductPack unwrapPack(ProductComponent component) {
        if (component instanceof ProductPack p) return p;
        if (component instanceof GiftWrapDecorator g) return unwrapPack(g.getWrapped());
        return null;
    }

    // MÉTHODES DE LECTURE SÉCURISÉE
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrée invalide, veuillez entrer un nombre entier.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrée invalide, veuillez entrer un nombre décimal (ex: 12.5).");
            }
        }
    }
}
