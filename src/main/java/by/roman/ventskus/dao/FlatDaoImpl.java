package by.roman.ventskus.dao;

import by.roman.ventskus.dto.Filter;
import by.roman.ventskus.entity.Flat;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@Repository
public class FlatDaoImpl extends BaseDaoImpl<Flat> implements FlatDao {

    public FlatDaoImpl() {
        super(Flat.class);
    }

    @Override
    public List<Flat> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Flat> cq = cb.createQuery(Flat.class);
        Root<Flat> rootEntry = cq.from(Flat.class);

        CriteriaQuery<Flat> all = cq.select(rootEntry).orderBy(cb.asc(rootEntry.get("parsedDate")));
        TypedQuery<Flat> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<Flat> find(Filter filter) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Flat> cq = cb.createQuery(Flat.class);
        Root<Flat> rootEntry = cq.from(Flat.class);

        CriteriaQuery<Flat> all = cq.select(rootEntry);
        if (filter != null) {
            Predicate defaultPredicate = cb.ge(rootEntry.<Number>get("price"), 0);
            Predicate predicateGe = filter.getPriceStart() != null ? cb.ge(rootEntry.<Number>get("price"), filter.getPriceStart()) : defaultPredicate;
            Predicate predicateLe = filter.getPriceEnd() != null ? cb.le(rootEntry.<Number>get("price"), filter.getPriceEnd()) : defaultPredicate;
            Predicate predicateNearForMetro = filter.getOnlyNearMetro() != null && filter.getOnlyNearMetro() ? cb.isTrue(rootEntry.<Boolean>get("nearForMetro")) : defaultPredicate;
            all = all.where(cb.and(cb.and(predicateGe, predicateLe), predicateNearForMetro));
        }
        all = all.orderBy(cb.desc(rootEntry.get("parsedDate")));
        TypedQuery<Flat> allQuery = getEntityManager().createQuery(all).setFirstResult(filter.getOffset()).setMaxResults(filter.getLimit());
        return allQuery.getResultList();
    }
}
