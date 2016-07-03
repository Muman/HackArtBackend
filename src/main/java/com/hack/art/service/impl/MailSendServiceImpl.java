package com.hack.art.service.impl;

import com.hack.art.exception.MailSendException;
import com.hack.art.service.MailSendService;
import com.hack.art.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;


/**
 * Created by ROLO on 02.04.2016.
 */
@Service
@Transactional
public class MailSendServiceImpl implements MailSendService {

    @Value("${mail.template}")
    private String test;

    @Value("${mail.host}")
    private String host = "smtp.gmail.com";

    @Value("${mail.senderUser}")
    private String user;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.template}")
    private String template;

    @Value("${mail.sender}")
    private String sender;

    @Autowired
    private LogUtil logger;

    public void sendMail(String email, String subject, String body, File... attachment) {
        Properties properties = System.getProperties();

        logger.info("test template", test);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.user", user);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);

            BodyPart messageBody = new MimeBodyPart();
            messageBody.setContent(String.format(template, body), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBody);

            if (attachment.length > 0) {
                for (int i = 0; i < attachment.length; i++) {
                    messageBody = new MimeBodyPart();
                    messageBody.setFileName(attachment[i].getName());
                    messageBody.setDataHandler(new DataHandler(new FileDataSource(attachment[i])));
                    multipart.addBodyPart(messageBody);
                }
            }

            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new MailSendException(e);
        }
    }

}
