/*
 * Created by Nicholas Blantz on 2018.11.22  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.controllers;

import com.airaid.alerting.AlertTask;
import com.airaid.alerting.AlertTimer;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Nick Blantz
 */
@Named(value = "textMessageController")
@RequestScoped
public class AlertController {
    private String recipientNumber;
    private String recipientCarrier;
    private String messageContent;
    private Date messageDate;
    private Long pretime;
    @Inject private AlertTimer alertTimer;
    // TODO: Alert needs to be associated with each Ticket
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
        this.pretime = pretime;
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
        pretime = null;
    }
    
    public void createAlert() {
        alert = new AlertTask(recipientNumber, recipientCarrier, messageContent);
        alertTimer.scheduleAlert(alert, messageDate, pretime);
    }
    
    
}
