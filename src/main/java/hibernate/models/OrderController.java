package hibernate.models;

import hibernate.models.Order;
import hibernate.storage.Product;

import java.util.List;

/**
 * Created by Андрей on 18.04.2018.
 */
public class OrderController {
    private Order order;

    public void makeOrder(String address, String payment, List<Product> products) {
        this.order = new Order(address, payment, products);
    }

    public Order getOrder() {
        return this.order;
    }
}
