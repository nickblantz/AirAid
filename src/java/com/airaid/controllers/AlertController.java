/*
 * Created by Nicholas Blantz on 2018.11.22  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.controllers;

import com.airaid.EntityBeans.User;
import com.airaid.alerting.AlertTask;
import com.airaid.alerting.AlertTimer;
import com.airaid.globals.Methods;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Nick Blantz
 */
@Named(value = "alertController")
@RequestScoped
public class AlertController {
    private String recipientNumber;
    private String recipientCarrier;
    private String messageContent;
    private Date messageDate;
    private String messageDateString;
    private Long pretime = 0L;
    @Inject private AlertTimer alertTimer;
    @Inject private FlightSearchController flightSearchController;
    private AlertTask alert;
    
    public AlertController() {}
    
    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public Long getPretime() {
        return pretime;
    }

    public void setPretime(Long pretime) {
        if (pretime == null) {
            this.pretime = 0L;
        }
        this.pretime = pretime * 60000;
    }
    
    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public String getRecipientCarrier() {
        return recipientCarrier;
    }

    public void setRecipientCarrier(String recipientCarrier) {
        this.recipientCarrier = recipientCarrier;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    
    public void clearFields() {
        recipientNumber = null;
        recipientCarrier = null;
        messageContent = null;
        messageDate = null;
        pretime = 0L;
    }
    
    public void createAlert() {
        // Gets expected departure date from session's FlightSearchController
        messageDate = flightSearchController.getSelected().getExpectedDepartureDate();
        
        // Gets phone number and carrier from current logged in user
        User user = (User) Methods.sessionMap().get("user");
        recipientNumber = user.getPhoneNumber();
        recipientCarrier = user.getMobileCarrier();
        messageContent = "Time to catch your flight!";
        
        // Creates a new alert and schedules it
        alert = new AlertTask(recipientNumber, recipientCarrier, messageContent);
        alertTimer.scheduleAlert(alert, messageDate, pretime);
    }
    
    public void setPretime(String time) {
        pretime = Long.parseLong(time) * 60000;
    }
    
    public void setMessageDate(String date) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        messageDate = parser.parse(date);
    }
    
    public String getMessageDateString() {
        return messageDateString;
    }

    public void setMessageDateString(String messageDateString) throws ParseException {
        this.messageDateString = messageDateString;
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        messageDate = parser.parse(messageDateString);
    }
}
