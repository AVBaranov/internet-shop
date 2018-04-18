package hibernate.storage;

import javax.persistence.*;

/**
 * Created by Андрей on 17.04.2018.
 */
@Entity
@Table(name="products")
public class Product {
    private int id;
    private String name;
    private int price;
    private boolean sold;
    private Category category;

    public Product() {
    }

    public Product(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(String name, int price, boolean sold, Category category) {
        this.name = name;
        this.price = price;
        this.sold = sold;
        this.category = category;
    }

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name="sold", columnDefinition = "boolean default false")
    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (price != product.price) return false;
        if (sold != product.sold) return false;
        if (!name.equals(product.name)) return false;
        return category.equals(product.category);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        result = 31 * result + (sold ? 1 : 0);
        result = 31 * result + category.hashCode();
        return result;
    }
}
