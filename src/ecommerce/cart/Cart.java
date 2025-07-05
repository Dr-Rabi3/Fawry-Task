package ecommerce.cart;

import ecommerce.interfaces.Shippable;
import ecommerce.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    Map<Integer, CartItem> items = new HashMap<>();

    public void add(Product product, int quantity) throws Exception {
        CartItem item = items.get(product.getId());
        // Try to reserve total amount including current cart quantity
        // you don’t care about what’s already in the cart — that quantity is already deducted.
        if (!product.reserve(quantity)) {
            throw new Exception("Product " + product.getName() + " does not have enough stock");
        }

        if(item == null){
            CartItem newItem = new CartItem(product, quantity);
            items.put(product.getId(), newItem);
        }
        else {
            item.quantity += quantity;
        }
    }

    public boolean isEmpty() {return items.isEmpty();}

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.quantity * item.product.getPrice();
        }
        return total;
    }

    public List<Shippable> getShippableItems (){
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items.values()) {
            if(item.product instanceof Shippable){
                Shippable shippable = (Shippable) item.product;
                for (int i = 0; i < item.quantity; i++) {
                    shippableItems.add(shippable);
                }
            }
        }
        return shippableItems;
    }
}
