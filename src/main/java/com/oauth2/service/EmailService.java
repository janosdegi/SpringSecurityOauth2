package com.oauth2.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created by Dégi János on 2017.10.07..
 */
@Service
public class EmailService implements IEmailService {



//    public static void main(String[] args) {
//        sendEmail();
//    }

    public void sendEmail(String body) {

//        https://app.mailjet.com/transactional/smtp
//        auth: javajeecoder@gmail.com
//        pass: 1frcfgtxemz.

        final String username = "3ac50595e6fe08c8eb388fed3e75153e";
        final String password = "c682ef467e79b33d96807b88f0d8c12d";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "in-v3.mailjet.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("javajeecoder@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("johann.degi@gmail.com"));
            message.setSubject("Testing Subject");
//            message.setText("Dear Mr. John Degi,"
//                    + "\n\n You are the best!");
            message.setText(body);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) throws MailjetException, MailjetSocketTimeoutException {
//        MailjetClient client;
//        MailjetRequest request;
//        MailjetResponse response;
//        client = new MailjetClient("3ac50595e6fe08c8eb388fed3e75153e", "c682ef467e79b33d96807b88f0d8c12d");
//        request = new MailjetRequest(Email.resource)
//                .property(Email.FROMEMAIL, "pilot@mailjet.com")
//                .property(Email.FROMNAME, "Mailjet Pilot")
//                .property(Email.SUBJECT, "Your email flight plan!")
//                .property(Email.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
//                .property(Email.HTMLPART, "Dear passenger, welcome to Mailjet!May the delivery force be with you!")
//                .property(Email.RECIPIENTS, new JSONArray()
//                        .put(new JSONObject()
//                                .put("Email", "javajeecoder@gmail.com")));
//        response = client.post(request);
//        System.out.println(response.getData());
//    }
//
//    public static void main(String[] args) {
//
//        final String username = "javajeecoder@gmail.com";
//        final String password = "";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("javajeecoder@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("johann.degi@gmail.com"));
//            message.setSubject("Testing Subject");
//            message.setText("Dear Mail Crawler,"
//                    + "\n\n No spam to my email, please!");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
