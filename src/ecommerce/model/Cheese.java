package ecommerce.model;

import ecommerce.interfaces.Expirable;
import ecommerce.interfaces.Shippable;

import java.time.LocalDate;

public class Cheese extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weight;

    public Cheese(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity); // ✅ calls the new constructor without ID argument
        this.expiryDate = expiryDate;
        this.weight = weight;
    }
    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public LocalDate getExpirationDate() {
        return expiryDate;
    }

    @Override
    public boolean isShippable() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
