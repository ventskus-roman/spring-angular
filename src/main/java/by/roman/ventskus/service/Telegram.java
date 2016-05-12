package by.roman.ventskus.service;

import by.roman.ventskus.telegram.framework.telegram.TelegramAPI;
import by.roman.ventskus.telegram.framework.telegram.TelegramRealApi;
import org.springframework.stereotype.Service;

/**
 * Created by Roman Ventskus on 13.05.2016.
 */
@Service
public class Telegram {

    private TelegramAPI api;

    public Telegram() {
        String botName = System.getProperty("BotName");
        String botPassword = System.getProperty("BotPassword");
        this.api = new TelegramRealApi() {
            public String getBotUsername() {
                return botName;
            }

            public String getBotToken() {
                return botPassword;
            }
        };
    }

    public TelegramAPI getApi() {
        return api;
    }

}
