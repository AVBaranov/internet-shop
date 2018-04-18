package hibernate;

import hibernate.models.Busket;
import hibernate.models.OrderController;
import hibernate.storage.Category;
import hibernate.storage.Product;
import hibernate.storage.ProductDaoStorage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Андрей on 02.04.2018.
 */
public class Main {
    public static void main (String[] args) {


        //добавление данных в бд
        new ProductDaoStorage().create(new Product("boots", 1000, new Category("clothes")));
        new ProductDaoStorage().create(new Product("computer", 1000, new Category("electronics")));

        //добавление продуктов в корзину и удаление из бд
        Busket busket = new Busket();
        busket.addToBusket(new ProductDaoStorage().findById(1));

        //выполнение заказа
        OrderController controller = new OrderController();
        controller.makeOrder("usa", "cash", busket.getBusket());
        //вывод сделаного заказа на консоль
        System.out.println(controller.getOrder());


    }
}
