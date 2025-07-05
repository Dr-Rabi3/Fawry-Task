package ecommerce.customer;

import ecommerce.cart.Cart;
import ecommerce.cart.CartItem;
import ecommerce.interfaces.Expirable;
import ecommerce.interfaces.Shippable;
import ecommerce.model.Product;
import ecommerce.service.ShippingService;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final int id;
    private String name;
    private String email;
    private double balance;
    private Cart cart = new Cart();

    private static int nextId = 1;

    public Customer(String name, String email, double balance) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Cart getCart() {
        return cart;
    }

    public void checkout() throws Exception{
        if(cart.isEmpty()) throw new Exception("Cart is empty");

        for(CartItem item : cart.getItems()){
            Product p = item.product;
            if (p instanceof Expirable exp && exp.isExpired()) {
                throw new Exception(p.getName() + " is expired.");
            }
        }
        List<Shippable> shippableItems = cart.getShippableItems();
        double subtotal = cart.getSubtotal();
        double shippingFees = shippableItems.stream().mapToDouble(Shippable::getWeight).sum() * 2; // shipping fees of 2 curency units per kg
        double total = subtotal + shippingFees;

        if (total > balance) throw new Exception("Insufficient balance");
        balance -= total;
        for (CartItem item : cart.getItems()) {
            item.product.reduceQuantity(item.quantity);
        }

        ShippingService.shipItems(shippableItems);

        System.out.println("+----------- Checkout Summary -----------+");
        System.out.printf("| %-20s | $%14.2f |\n", "Subtotal", subtotal);
        System.out.printf("| %-20s | $%14.2f |\n", "Shipping Fee", shippingFees);
        System.out.printf("| %-20s | $%14.2f |\n", "Total Paid", total);
        System.out.printf("| %-20s | $%14.2f |\n", "Remaining Balance", balance);
        System.out.println("+----------------------+-----------------+");

    }
}
