package com.ecommerce.Patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class CategoryComposite implements ProductComponent {

    private String name;
    private List<ProductComponent> children = new ArrayList<>();

    public CategoryComposite(String name) {
        this.name = name;
    }

    public void addComponent(ProductComponent component) {
        children.add(component);
    }

    public void removeComponent(ProductComponent component) {
        children.remove(component);
    }

    public List<ProductComponent> getChildren() {
        return children;
    }

    @Override
    public double getPrice() {
        return children.stream().mapToDouble(ProductComponent::getPrice).sum();
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder(name + " : [");
        for (ProductComponent c : children) {
            desc.append(c.getDescription()).append(", ");
        }
        if (!children.isEmpty()) desc.setLength(desc.length() - 2); // retire la dernière virgule
        desc.append("]");
        return desc.toString();
    }

    @Override
    public String getName() {
        return name;
    }

}
