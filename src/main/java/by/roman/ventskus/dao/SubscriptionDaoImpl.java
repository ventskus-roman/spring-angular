package by.roman.ventskus.dao;

import by.roman.ventskus.entity.Subscription;
import org.springframework.stereotype.Repository;

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
@Repository
public class SubscriptionDaoImpl extends BaseDaoImpl<Subscription> implements SubscriptionDao {

    public SubscriptionDaoImpl() {
        super(Subscription.class);
    }
}
