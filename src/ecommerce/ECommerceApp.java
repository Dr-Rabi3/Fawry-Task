package ecommerce;

import ecommerce.customer.Customer;
import ecommerce.model.*;

import java.time.LocalDate;

public class ECommerceApp {
    public static void main(String[] args) {
        try {
            Cheese cheese = new Cheese("Cheddar Cheese", 10, 5, LocalDate.now().plusDays(3), 1.0);
            Biscuits biscuits = new Biscuits("Digestive", 5, 10, LocalDate.now().plusDays(2), 0.6);
            TV tv = new TV("Samsung TV", 300, 2, 10);
            Mobile mobile = new Mobile("iPhone", 1000, 3, 8);
            ScratchCard card = new ScratchCard("Recharge Card", 3, 50);

            Customer customer = new Customer("Mohamed", "abdalrazekmohmed@gamil.com" , 1500);

            customer.getCart().add(cheese, 2);
            customer.getCart().add(tv, 1);
            customer.getCart().add(card, 3);

            customer.checkout();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // example for buy last piece by to customer
        Product rareItem = new Mobile("Limited Edition Phone", 999, 1, 9);
        Customer customer1 = new Customer("Alice", "user1@gmail.com",2000);
        Customer customer2 = new Customer("Bob", "user2@gmail.com", 2000);
        try {
            customer1.getCart().add(rareItem, 1);
            customer2.getCart().add(rareItem, 1); // will fail
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            customer1.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            customer2.checkout(); // Should throw error — no stock
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
