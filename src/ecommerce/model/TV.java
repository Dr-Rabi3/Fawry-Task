package ecommerce.model;

import ecommerce.interfaces.Shippable;

public class TV extends Product implements Shippable {
    private double weight;

    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean isShippable() { return true; }

    @Override
    public double getWeight() { return weight; }
}
