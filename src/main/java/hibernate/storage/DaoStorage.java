package hibernate.storage;

import java.util.List;

/**
 * Created by Андрей on 03.04.2018.
 */
public interface DaoStorage <E> {
    void create(E entity);
    List<E> read();
    void update(int id, E entity);
    void delete(int id);
    E findById(int id);
}
