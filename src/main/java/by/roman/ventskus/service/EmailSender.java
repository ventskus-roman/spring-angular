package by.roman.ventskus.service;

import by.roman.ventskus.entity.Flat;
import by.roman.ventskus.entity.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
@Service
public class EmailSender {

    private static final String TEMPLATE = "<h2>%s$</h2><br/><h3>%s</h3><br/><a href=\"%s\">%s</a><br/><img src=\"%s\"/><br/><hr/><br/>";
    private static final String GREETING = "<h1>Привет, это мы, квартиры! И мы хотим, чтобы ты <a href=\"http://188.166.67.18:8080\">снял нас</a>!</h1><br/><hr/><br/>";
    private static final String FOOTER = "<p>Параметры вашей подписки: цена от %s$ до %s$, обязательно рядом с метро - %s</p>";
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendFlats(final List<Flat> flats, Subscription subscription) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setTo(subscription.getEmail());
            helper.setFrom("noreply@flatsminsk.by");
            helper.setSubject("Аренда");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(GREETING);
            for (Flat flat : flats) {
                stringBuilder.append(String.format(TEMPLATE, flat.getPrice(), flat.getAddress(), flat.getLink(), flat.getLink(), flat.getPhotoLink()));
            }
            stringBuilder.append(String.format(FOOTER,subscription.getPriceStart(), subscription.getPriceEnd(), subscription.getOnlyNearMetro() ? "да" : "нет"));
            helper.setText(stringBuilder.toString(), true);
            this.mailSender.send(message);
        }
        catch (Exception e) {
            LOGGER.warn("Can`t sent mail on " + subscription.getEmail(), e);
        }
    }


}
