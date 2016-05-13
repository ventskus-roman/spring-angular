package by.roman.ventskus.service;

import by.roman.ventskus.dao.SubscriptionDao;
import by.roman.ventskus.entity.Flat;
import by.roman.ventskus.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
@Service
public class NofiticationServiceImpl implements NotificationService {

    @Autowired
    private TelegramSender telegramSender;

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Override
    public void notify(List<Flat> flats) {
        List<Subscription> subscriptions = subscriptionDao.findAll();
        for (Subscription subscription : subscriptions) {
            sendIfNeed(subscription, flats);
        }
    }

    private void sendIfNeed(Subscription subscription, List<Flat> flats) {
        List<Flat> needToSend = getMatched(flats, subscription);
        if (!needToSend.isEmpty()) {
            telegramSender.sendFlats(needToSend, subscription);
        }
    }

    private List<Flat> getMatched(List<Flat> original, Subscription subscription) {
        List<Flat> result = new ArrayList<Flat>();
        for (Flat flat : original) {
            if (match(flat, subscription)) {
                result.add(flat);
            }
        }
        return result;
    }

    private boolean match(Flat flat, Subscription subscription) {
        boolean startPriceMatched = flat.getPrice() >= subscription.getPriceStart();
        boolean endPriceMatched = flat.getPrice() <= subscription.getPriceEnd();
        boolean metroMatched = !subscription.getOnlyNearMetro() || (subscription.getOnlyNearMetro() && flat.getNearForMetro());
        return startPriceMatched && endPriceMatched && metroMatched;
    }

}
