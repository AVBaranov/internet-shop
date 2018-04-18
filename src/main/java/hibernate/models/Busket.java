package hibernate.models;

import hibernate.exceptions.NoSuchProductException;
import hibernate.storage.Product;
import hibernate.storage.ProductDaoStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 17.04.2018.
 */
public class Busket {
    private List<Product> busket = new ArrayList<>();
    private List<Product> products;

    public Busket() {
        this.products = new ProductDaoStorage().read();
    }

    public void addToBusket(Product product) {
        if (products.isEmpty()) {
            throw new NoSuchProductException("Product storage is empty !");
        }
        for (int i = 0; i < products.size(); i++) {
            if (product.equals(products.get(i))) {
                this.busket.add(product);
                new ProductDaoStorage().delete(product.getId());
                System.out.println("product have been added to busket");
                break;
            }
        }


    }

    public List<Product> getBusket() {
        return this.busket;
    }
}
