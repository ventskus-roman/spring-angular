package by.roman.ventskus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("*");
        sender.setPassword("*");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        sender.setJavaMailProperties(properties);

        return sender;
    }
}
