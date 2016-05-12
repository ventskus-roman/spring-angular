package by.roman.ventskus.service;

import by.roman.ventskus.entity.Flat;
import by.roman.ventskus.entity.Subscription;
import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.User;
import by.roman.ventskus.telegram.framework.entity.response.ManyMessagesResponse;
import by.roman.ventskus.telegram.framework.entity.response.SimpleManyMessageResponse;
import by.roman.ventskus.telegram.framework.telegram.send.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Roman Ventskus on 13.05.2016.
 */
@Service
public class TelegramSender {

    @Autowired
    private Telegram telegram;

    public void sendFlats(final List<Flat> flats, Subscription subscription) {
        Sender sender = new Sender(telegram.getApi());
        List<String> messages = flats.stream()
                .map(flat -> flat.getPrice() + "$\n" + flat.getLink())
                .collect(Collectors.toList());
        ManyMessagesResponse response = new SimpleManyMessageResponse(messages, new Command(""), new User(subscription.getUserId()));
        sender.send(response);
    }
}
