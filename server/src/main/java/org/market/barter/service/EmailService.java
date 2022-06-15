package org.market.barter.service;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.market.barter.command.SendEmailCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Value( "${email}" )
  private String email;

  @Value( "${password}" )
  private String password;

  @Value( "${host}" )
  private String host;

  public void sendEmail(SendEmailCommand command) throws MessagingException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, password);
      }
    });
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(email, false));

    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(command.getReceiver()));
    msg.setSubject(command.getSubject());
    msg.setContent(command.getContent(), "text/html");
    msg.setSentDate(new Date());
    Transport.send(msg);
  }
}
