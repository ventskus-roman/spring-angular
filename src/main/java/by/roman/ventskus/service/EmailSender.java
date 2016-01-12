package by.roman.ventskus.service;

import by.roman.ventskus.entity.Flat;
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

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
@Service
public class EmailSender {

    private static final String TEMPLATE = "<h2>%s$</h2><br/><h3>%s</h3><br/><a href=\"%s\">%s</a><br/><img src=\"%s\"/>";

    @Autowired
    private JavaMailSender mailSender;

    public void sendFlat(final Flat flat, final String address) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setTo(address);
            helper.setFrom("noreply@flatsminsk.by");
            helper.setSubject("Аренда");
            helper.setText(String.format(TEMPLATE, flat.getPrice(), flat.getAddress(), flat.getLink(), flat.getLink(), flat.getPhotoLink()), true);
            this.mailSender.send(message);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
