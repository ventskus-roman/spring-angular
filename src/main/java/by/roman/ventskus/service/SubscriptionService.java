package by.roman.ventskus.service;

import by.roman.ventskus.entity.Subscription;

import java.util.List;

/**
 * Created by Roman Ventskus on 22.01.2016.
 */
public interface SubscriptionService {

    String create(Subscription subscription);

    boolean delete(Long id);

    boolean updateAndActivate(Subscription subscription);

    List<Subscription> getAll();

    Subscription get(Long id);

    Subscription findByUserId(String userId);

}
