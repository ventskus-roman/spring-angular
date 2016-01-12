package by.roman.ventskus.service;

import by.roman.ventskus.dao.SubscriptionDao;
import by.roman.ventskus.entity.Flat;
import by.roman.ventskus.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
@Service
public class NofiticationServiceImpl implements NotificationService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Override
    public void notify(Flat flat) {
        List<Subscription> subscriptions = subscriptionDao.findAll();
        for (Subscription subscription : subscriptions) {
            notifyIfMatch(subscription, flat);
        }
    }

    private void notifyIfMatch(Subscription subscription, Flat flat) {
        if (flat.getPrice() <= subscription.getPriceEnd() && flat.getPrice() >= subscription.getPriceStart()
                && (!subscription.getOnlyNearMetro() || (subscription.getOnlyNearMetro() && flat.getNearForMetro()))) {
            emailSender.sendFlat(flat, subscription.getEmail());
        }
    }

}
