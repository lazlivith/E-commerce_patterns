package com.ecommerce.Patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class ProductPack implements ProductComponent {

    private final String description;
    private final List<ProductComponent> components = new ArrayList<>();

    public ProductPack(String description) {
        this.description = description;
    }

    // Ajouter un composant au pack
    public void addComponent(ProductComponent component) {
        components.add(component);
    }

    // Supprimer un composant par index
    public void removeComponent(int index) {
        if (index >= 0 && index < components.size()) {
            components.remove(index);
        }
    }

    // Obtenir les composants pour modification
    public List<ProductComponent> getComponents() {
        return components;
    }

    @Override
    public double getPrice() {
        return components.stream()
                .mapToDouble(ProductComponent::getPrice)
                .sum();
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(description + " [");
        for (ProductComponent c : components) {
            sb.append(c.getDescription()).append(", ");
        }
        if (!components.isEmpty()) sb.setLength(sb.length() - 2); // enlever la dernière virgule
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String getName() {
        return description;
    }

}
