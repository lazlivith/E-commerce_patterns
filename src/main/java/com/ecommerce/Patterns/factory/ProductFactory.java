package com.ecommerce.Patterns.factory;


import com.ecommerce.Patterns.composite.ProductComponent;
import com.ecommerce.domain.User;

public interface ProductFactory {

    // Créer un produit simple (éventuellement décoré)
    ProductComponent createProduct(String name, double price, boolean giftWrap);

    // Créer un pack (accessible seulement si admin)
    ProductComponent createPack(String description, ProductComponent[] components, boolean giftWrap, User user);
}
