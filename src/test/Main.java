package test;

import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.ui.console.ConsoleUI;
import com.ecommerce.Patterns.singleton.Logger;

public class Main {

    public static void main(String[] args) {

        Logger.getInstance().log("Démarrage de l'application e-commerce");

        //  Repositories partagés
        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();

        //  Injection des repositories
        ConsoleUI ui = new ConsoleUI(productRepository, orderRepository);
        ui.start();

        Logger.getInstance().log("Arrêt de l'application");
    }
}
