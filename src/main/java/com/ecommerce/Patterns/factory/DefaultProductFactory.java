package com.ecommerce.Patterns.factory;

import com.ecommerce.Patterns.composite.ProductComponent;
import com.ecommerce.Patterns.composite.ProductPack;
import com.ecommerce.Patterns.composite.SingleProduct;
import com.ecommerce.Patterns.decorator.GiftWrapDecorator;
import com.ecommerce.domain.User;

public class DefaultProductFactory implements ProductFactory {

    @Override
    public ProductComponent createProduct(String name, double price, boolean giftWrap) {
        ProductComponent product = new SingleProduct(name, price);
        if (giftWrap) {
            product = new GiftWrapDecorator(product); // décorateur
        }
        return product;
    }

    @Override
    public ProductComponent createPack(String description, ProductComponent[] components, boolean giftWrap, User user) {
        if (!user.isAdmin()) {
            System.out.println("[ERROR] Seul l'admin peut créer un pack !");
            return null;
        }

        ProductPack pack = new ProductPack(description);
        for (ProductComponent c : components) {
            pack.addComponent(c);
        }

        if (giftWrap) {
            return new GiftWrapDecorator(pack);
        }
        return pack;
    }
}
