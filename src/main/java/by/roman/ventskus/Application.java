package by.roman.ventskus;

import by.roman.ventskus.telegram.framework.Framework;
import by.roman.ventskus.telegram.framework.entity.FrameworkParams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Roman Ventskus on 06.12.2015.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        String botName = System.getProperty("BotName");
        String botPassword = System.getProperty("BotPassword");
        Framework.init(new FrameworkParams(botName, botPassword));
    }

}
