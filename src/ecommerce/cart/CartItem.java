package ecommerce.cart;

import ecommerce.model.Product;

public class CartItem {
    public Product product;
    public int quantity;


    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
