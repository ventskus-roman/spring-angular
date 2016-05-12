package by.roman.ventskus.rest;

import by.roman.ventskus.entity.Subscription;
import by.roman.ventskus.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Roman Ventskus on 22.01.2016.
 */
@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionResource {

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(Subscription subscription) {
        subscriptionService.create(subscription);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public boolean update(@RequestParam("id") Long id, @RequestParam("priceStart") Integer priceStart,
                          @RequestParam("priceEnd") Integer priceEnd, @RequestParam("onlyNearMetro") Boolean onlyNearMetro,
                          @RequestParam("acceptCode") String acceptCode) {
        Subscription subscription = new Subscription();
        subscription.setId(id);
        subscription.setPriceStart(priceStart);
        subscription.setPriceEnd(priceEnd);
        subscription.setOnlyNearMetro(onlyNearMetro);
        subscription.setAcceptCode(acceptCode);
        return subscriptionService.updateAndActivate(subscription);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean update(@RequestParam("id") Long id, @RequestParam("acceptCode") String acceptCode) {
        return subscriptionService.delete(id, acceptCode);
    }
}
