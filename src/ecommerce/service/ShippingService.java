package ecommerce.service;

import ecommerce.interfaces.Shippable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class CountWeight {
    int count = 0;
    double weight = 0;
}
public class ShippingService {
    public static void shipItems(List<Shippable> items){
        System.out.println("\nShipping Items:");

        Map<String, CountWeight> productMap = new LinkedHashMap<>();

        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();

            productMap.putIfAbsent(name, new CountWeight());
            CountWeight info = productMap.get(name);
            info.count++;
            info.weight = weight;
        }

        for (Map.Entry<String, CountWeight> entry : productMap.entrySet()) {
            String name = entry.getKey();
            CountWeight info = entry.getValue();
            System.out.printf("- %s (x%d) — %.2fkg each\n", name, info.count, info.weight);
        }
    }
}
