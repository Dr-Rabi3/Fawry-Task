package ecommerce.service;

import ecommerce.interfaces.Shippable;

import java.util.List;

public class ShippingService {
    public static void shipItems(List<Shippable> items){
        System.out.println("\nShipping Items:");
        for (Shippable item : items) {
            System.out.println("- " + item.getName() + " (" + item.getWeight() + "kg)");
        }
    }
}
