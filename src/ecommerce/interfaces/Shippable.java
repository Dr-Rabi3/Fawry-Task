package ecommerce.interfaces;

public interface Shippable {
    String getName(); // not duplicated. just interface that can be implemented by any class not necessarily Product.
    double getWeight();
}
