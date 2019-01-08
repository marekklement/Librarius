package cz.librarius.repository.dao;

import java.util.List;

public interface Repository<T> {
    List<T> list();

    T save(T entity);

    void delete(Object id);

    T find(Object id);

    T update(T entity);
}
