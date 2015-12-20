package by.roman.ventskus.dao;

import java.util.List;

/**
 * Created by Roman Ventskus on 20.12.2015.
 */
public interface BaseDao<T> {

    List<T> findAll();

    T get(Long id);

    void delete(Long id);

    void save(T entity);
}
