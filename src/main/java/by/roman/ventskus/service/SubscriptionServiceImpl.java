package by.roman.ventskus.service;

import by.roman.ventskus.dao.SubscriptionDao;
import by.roman.ventskus.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by Roman Ventskus on 22.01.2016.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Override
    public String create(Subscription subscription) {
        if (subscription.getEmail() == null) {
            throw new RuntimeException("Empty email");
        }
        if (subscriptionDao.isEmailRegistered(subscription.getEmail())) {
            throw new RuntimeException("Already registered email");
        }
        String key = UUID.randomUUID().toString();
        subscription.setId(null);
        subscription.setAcceptCode(key);
        subscriptionDao.save(subscription);
        return key;
    }

    @Override
    public boolean delete(Long id) {
        subscriptionDao.delete(id);
        return true;
    }

    @Override
    public boolean updateAndActivate(Subscription subscription) {
        if (subscription == null || subscription.getId() == null) {
            throw new RuntimeException("Empty subscription or subscription id");
        }
        Long id = subscription.getId();
        Subscription readed = subscriptionDao.get(id);
        String oldKey = readed.getAcceptCode();
        String newKey = UUID.randomUUID().toString();
        if (oldKey.equals(subscription.getAcceptCode())) {
            subscription.setEmail(readed.getEmail());
            subscription.setAcceptCode(newKey);
            subscription.setActive(true);
            subscriptionDao.save(subscription);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionDao.findAll();
    }

    @Override
    public Subscription get(Long id) {
        return subscriptionDao.get(id);
    }

    @Override
    public Subscription findByUserId(String userId) {
        return subscriptionDao.findByUserId(userId);
    }
}
