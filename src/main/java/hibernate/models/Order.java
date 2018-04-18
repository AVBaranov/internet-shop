package hibernate.models;

import hibernate.storage.Product;

import java.util.List;

/**
 * Created by Андрей on 17.04.2018.
 */
public class Order {
    private int id;
    private String address;
    private String payment;
    private List<Product> products;


    public Order(String address, String payment, List<Product> products) {
        this.address = address;
        this.payment = payment;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", payment='" + payment + '\'' +
                ", products=" + products +
                '}';
    }
}
