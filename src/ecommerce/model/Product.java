package ecommerce.model;

public abstract class Product {
    protected final int id; // prevent generate id by subclass
    protected String name;
    protected double price;
    protected int quantity;

    private static int nextId = 1;

    public Product(String name, double price, int quantity) {
        this.id = nextId++;

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getId() { return id; }

    // to ensure no two user take last piece in same time
    public synchronized boolean reserve(int qty) {
        if (qty > quantity) {
            return false;
        }
        quantity -= qty;
        return true;
    }

    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    // all product have isExpired, isShippable, but if not implement their interface will return false
    // if implement them in interface, you should casting them anywhere use them (not clean and error-prone)
    public boolean isExpired() {
        return false;
    }
    public boolean isShippable() {
        return false;
    }
}
