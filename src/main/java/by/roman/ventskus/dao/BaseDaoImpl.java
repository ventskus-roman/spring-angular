package by.roman.ventskus.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Roman Ventskus on 20.12.2015.
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    private final Class entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public BaseDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public T get(Long id) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void delete(Long id) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void save(T entity) {
        throw new RuntimeException("Not implemented yet");
    }
}
