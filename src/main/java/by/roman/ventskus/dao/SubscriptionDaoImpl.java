package by.roman.ventskus.dao;

import by.roman.ventskus.entity.Subscription;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
@Repository
public class SubscriptionDaoImpl extends BaseDaoImpl<Subscription> implements SubscriptionDao {

    public SubscriptionDaoImpl() {
        super(Subscription.class);
    }

    @Override
    public Boolean isEmailRegistered(String email) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Subscription> cq = cb.createQuery(Subscription.class);
        Root<Subscription> rootEntry = cq.from(Subscription.class);

        CriteriaQuery<Subscription> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("email"), email));
        TypedQuery<Subscription> allQuery = getEntityManager().createQuery(all);
        return !allQuery.getResultList().isEmpty();
    }
}
