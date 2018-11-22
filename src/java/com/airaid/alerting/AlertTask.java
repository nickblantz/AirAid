/*
 * Created by Nicholas Blantz on 2018.11.22  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.alerting;

import java.util.Properties;
import java.util.TimerTask;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nick Blantz
 */
public class AlertTask extends TimerTask {
    
    private final String recipientNumber;
    private final String recipientCarrierDomain;
    private final String messageContent;
    
    public AlertTask(String recipientNumber, String recipientCarrierDomain, String messageContent) {
        this.recipientNumber = recipientNumber;
        this.recipientCarrierDomain = recipientCarrierDomain;
        this.messageContent = messageContent;
    }

    @Override
    public void run() {
        Properties smtpProperties = System.getProperties();
        smtpProperties.put("mail.smtp.port", "587");
        smtpProperties.put("mail.smtp.auth", "true");
        smtpProperties.put("mail.smtp.starttls.enable", "true");
        
        try {
            Session smtpSession = Session.getDefaultInstance(smtpProperties, null);
            MimeMessage textMessage = new MimeMessage(smtpSession);
            textMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientNumber + "@" + recipientCarrierDomain));
            textMessage.setContent(messageContent, "text/plain");
            Transport transport = smtpSession.getTransport("smtp");
            
            // Switch to airaid email
            transport.connect("smtp.gmail.com", "Cloud.Software.Email@gmail.com", "csd@VT-1872");
            transport.close();
        } catch (AddressException ae) {
            System.out.println("Email Address Exception Occurred! See: " + ae.getMessage());
        } catch (MessagingException me) {
            System.out.println("Email Messaging Exception Occurred! Internet Connection Required! See: " + me.getMessage());
        }
    }
    
}
