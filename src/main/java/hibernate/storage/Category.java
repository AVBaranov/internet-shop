package hibernate.storage;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Андрей on 17.04.2018.
 */
@Entity
@Table(name="categories")
public class Category {
    private int category_id;
    private String name;
    private List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (category_id != category.category_id) return false;
        return name != null ? name.equals(category.name) : category.name == null;
    }

    @Override
    public int hashCode() {
        int result = category_id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
