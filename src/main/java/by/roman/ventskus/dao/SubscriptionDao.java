package by.roman.ventskus.dao;

import by.roman.ventskus.entity.Subscription;

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
public interface SubscriptionDao extends BaseDao<Subscription> {

    Boolean isEmailRegistered(String email);
}
