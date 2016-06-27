package by.roman.ventskus.controller;

import by.roman.ventskus.config.ApplicationContextProvider;
import by.roman.ventskus.entity.Subscription;
import by.roman.ventskus.service.SubscriptionService;
import by.roman.ventskus.telegram.framework.config.BotController;
import by.roman.ventskus.telegram.framework.config.Controller;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.AppealInParameterResponse;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.entity.response.SingleMessageResponse;

import java.util.Map;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@BotController(value = "/subscribe")
public class SimpleController implements Controller {

    private Parameter startPrice = new Parameter("startPrice");
    private Parameter endPrice = new Parameter("endPrice");
    private Parameter onlyNearMetro = new Parameter("onlyNearMetro");

    @Override
    public Response process(Request request, Map<Parameter, String> params) {
        if (!params.containsKey(startPrice)) {
            return new AppealInParameterResponse("Начиная от какой цены искать квартиры?", startPrice, request.getCommand(), request.getUser());
        } else if (!params.containsKey(endPrice)) {
            return new AppealInParameterResponse("До какой цены?", endPrice, request.getCommand(), request.getUser());
        } else if (!params.containsKey(onlyNearMetro)) {
            return new AppealInParameterResponse("Только возле метро?", onlyNearMetro, request.getCommand(), request.getUser());
        }
        SubscriptionService subscriptionService = ApplicationContextProvider.getApplicationContext().getBean("subscriptionServiceImpl", SubscriptionService.class);
        Subscription subscription = new Subscription(Integer.parseInt(params.get(startPrice)), Integer.parseInt(params.get(endPrice)), parseBoolean(params.get(onlyNearMetro)),
                "", true, "", request.getUser().getId());
        subscriptionService.create(subscription);
        Response response = new SingleMessageResponse("Ваша подписка сохранена!", request.getCommand(),request.getUser());
        return response;
    }

    private Boolean parseBoolean(String onlyNearMetro) {
        if (onlyNearMetro.toLowerCase().equals("yes") || onlyNearMetro.toLowerCase().equals("no")) {
            return Boolean.parseBoolean(onlyNearMetro);
        } else if (onlyNearMetro.toLowerCase().contains("да")) {
            return true;
        } else {
            return false;
        }
    }

}
