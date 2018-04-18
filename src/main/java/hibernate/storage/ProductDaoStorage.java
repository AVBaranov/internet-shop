package hibernate.storage;

import hibernate.exceptions.NoSuchProductException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 17.04.2018.
 */
public class ProductDaoStorage implements DaoStorage<Product> {
    private SessionFactory factory;

    public ProductDaoStorage() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void create(Product product) {
        if (this.factory.isClosed()) {
            this.factory = new Configuration().configure().buildSessionFactory();
        }
        Session session = this.factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(product.getCategory());
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            this.factory.close();
        }
    }

    @Override
    public List<Product> read() {
        if (this.factory.isClosed()) {
            this.factory = new Configuration().configure().buildSessionFactory();
        }
        Session session = this.factory.openSession();
        Transaction transaction = null;
        List<Product> products = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            products = session.createQuery("from Product").list();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            this.factory.close();
        }
        return products;
    }

    @Override
    public void update(int id, Product newProduct) {
        if (this.factory.isClosed()) {
            this.factory = new Configuration().configure().buildSessionFactory();
        }
        Session session = this.factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            newProduct.setId(id);
            session.save(newProduct.getCategory());
            session.saveOrUpdate(newProduct);
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            this.factory.close();
        }
    }

    @Override
    public void delete(int id) {
        if (this.factory.isClosed()) {
            this.factory = new Configuration().configure().buildSessionFactory();
        }
        Session session = this.factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            this.factory.close();
        }
    }

    @Override
    public Product findById(int id) {
        if (this.factory.isClosed()) {
            this.factory = new Configuration().configure().buildSessionFactory();
        }
        Session session = this.factory.openSession();
        Transaction transaction = null;
        Product product = null;
        try {
            transaction = session.beginTransaction();
            product = session.get(Product.class, id);
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            this.factory.close();
        }
        return product;
    }

    public void addToBusket(List<Product> busket) {
        if (this.factory.isClosed()) {
            this.factory = new Configuration().configure().buildSessionFactory();
        }
        Session session = this.factory.openSession();
        Transaction transaction = null;
        List<Product> products = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        try {
            products = session.createQuery("from Product").list();

            for (int i = 0; i < products.size(); i++) {
                for (int j = 0; j < busket.size(); j++) {
                    if (products.get(i).equals(busket.get(j))) {
                        numbers.add(i);
                        busket.remove(j);
                        break;
                    }
                }
            }
            if (busket.size() > 0) {
                System.out.println("something goes wrong");
                throw new NoSuchProductException("no such product");
            }
            for (Integer value : numbers) {
                System.out.println(products.get(value).getId());
                this.delete(products.get(value).getId());
//            session.delete(session.get(Product.class, products.get(value).getId()));
            }

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            this.factory.close();
        }
    }


}
