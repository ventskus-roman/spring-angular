package by.roman.ventskus.controller;

import by.roman.ventskus.config.ApplicationContextProvider;
import by.roman.ventskus.entity.Subscription;
import by.roman.ventskus.service.SubscriptionService;
import by.roman.ventskus.telegram.framework.config.BotController;
import by.roman.ventskus.telegram.framework.config.Controller;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.entity.response.SingleMessageResponse;

import java.util.Map;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@BotController(value = "/stop")
public class UnsubscribeController implements Controller {

    @Override
    public Response process(Request request, Map<Parameter, String> params) {
        SubscriptionService subscriptionService = ApplicationContextProvider.getApplicationContext().getBean("subscriptionServiceImpl", SubscriptionService.class);
        Subscription subscription = subscriptionService.findByUserId(request.getUser().getId());
        subscriptionService.delete(subscription.getId());
        Response response = new SingleMessageResponse("Окааай... До встречи!", request.getCommand(),request.getUser());
        return response;
    }

}
