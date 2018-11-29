/*
 * Created by Osman Balci on 2018.06.26
 * Copyright © 2018 Osman Balci. All rights reserved.
 */
package com.airaid.controllers;

import com.airaid.EntityBeans.User;
import com.airaid.globals.Methods;
import javax.inject.Named;
import java.util.Properties;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.airaid.FacadeBeans.UserFacade;
import javax.ejb.EJB;

/* 
 The @Named class annotation designates the bean object created by this class 
 as a Contexts and Dependency Injection (CDI) managed bean. The object reference
 of a CDI-managed bean can be @Inject'ed in another CDI-Managed bean so that
 the other CDI-managed bean can access the methods and properties of this bean.

 Using the Expression Language (EL) in a JSF XHTML page, you can invoke a CDI-managed
 bean's method or set/get its property by using the logical name given with the 'value'
 parameter of the @Named annotation, e.g., #{emailController.methodName() or property name}
 */
@Named(value = "emailController")
/* 
 The @RequestScoped annotation indicates that the user’s interaction with
 this CDI-managed bean will be active only in a single HTTP request.
 */
@RequestScoped

public class EmailController {

    /*
    ==================
    Constructor Method
    ==================
     */
    public EmailController() {
    }

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    
    private String cellPhoneNumber;         // Cell phone number to which MMS Text Message to be sent. 
    private String cellPhoneCarrierDomain;  // Cell phone carrier company's MMS gateway domain name.
    private String mmsTextMessage;          // MMS text message content. 

    Properties emailServerProperties;   // java.util.Properties
    Session emailSession;               // javax.mail.Session  
    MimeMessage mimeEmailMessage;  
    
    private String emailTo;             // Contains only one email address to send email to
    private String emailCc;             // Contains comma separated multiple email addresses with no spaces
    private String emailSubject;        // Subject line of the email message
    private String emailBody;           // Email content created in HTML format with PrimeFaces Editor
    private String key;
    @EJB
    private UserFacade userFacade;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    MimeMessage htmlEmailMessage;       // javax.mail.internet.MimeMessage

    /*
    ************************************************************************************************
    The import javax.inject.Inject; brings in the javax.inject package into our project. 
    "This package specifies a means for obtaining objects in such a way as to maximize 
    reusability, testability and maintainability compared to traditional approaches such as
    constructors, factories, and service locators (e.g., JNDI). This process, known as 
    dependency injection, is beneficial to most nontrivial applications." [Oracle] 
    
    The @Inject annotation of the instance variable "private EditorController editorController;" 
    directs the CDI Container Manager to store the object reference of the EditorController class
    bean object, after it is instantiated at runtime, into the instance variable "editorController".

    With this injection, the instance variables and instance methods of the EditorController
    class can be accessed in this CDI-managed bean.
    ************************************************************************************************
     */
    @Inject
    private EditorController editorController;

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public EditorController getEditorController() {
        return editorController;
    }

    public void setEditorController(EditorController editorController) {
        this.editorController = editorController;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public Properties getEmailServerProperties() {
        return emailServerProperties;
    }

    public void setEmailServerProperties(Properties emailServerProperties) {
        this.emailServerProperties = emailServerProperties;
    }

    public Session getEmailSession() {
        return emailSession;
    }

    public void setEmailSession(Session emailSession) {
        this.emailSession = emailSession;
    }

    public MimeMessage getHtmlEmailMessage() {
        return htmlEmailMessage;
    }

    public void setHtmlEmailMessage(MimeMessage htmlEmailMessage) {
        this.htmlEmailMessage = htmlEmailMessage;
    }
    
    

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailCc() {
        return emailCc;
    }

    public void setEmailCc(String emailCc) {
        this.emailCc = emailCc;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getCellPhoneCarrierDomain() {
        return cellPhoneCarrierDomain;
    }

    public void setCellPhoneCarrierDomain(String cellPhoneCarrierDomain) {
        this.cellPhoneCarrierDomain = cellPhoneCarrierDomain;
    }

    public String getMmsTextMessage() {
        return mmsTextMessage;
    }

    public void setMmsTextMessage(String mmsTextMessage) {
        this.mmsTextMessage = mmsTextMessage;
    }

    public MimeMessage getMimeEmailMessage() {
        return mimeEmailMessage;
    }

    public void setMimeEmailMessage(MimeMessage mimeEmailMessage) {
        this.mimeEmailMessage = mimeEmailMessage;
    }
    
    

    
    public String getRandomString()
    {
        String listChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random rand = new Random();
        StringBuilder str = new StringBuilder();
        while(str.length() < 5)
        {
            str.append(listChars.charAt(rand.nextInt(listChars.length())));
        }
        return str.toString();
    }
    /*
    ======================================================
    Create Email Sesion and Transport Email in HTML Format
    ======================================================
     */
    public void sendEmail(String str, String pin) throws AddressException, MessagingException {

        // Obtain the email message content from the editorController object
        UserFacade fa = this.getUserFacade();
        User u = fa.findByUsername(str);
        if (u.getIsVerified())
        {
            this.sendTextMessage(str, pin);
            return;
        }
        emailTo = u.getEmail();
        emailBody = "Your Verification Pin is: " + pin;
        emailSubject = "AirAid Verification Email";

        // Set Email Server Properties
        emailServerProperties = System.getProperties();
        emailServerProperties.put("mail.smtp.port", "587");
        emailServerProperties.put("mail.smtp.auth", "true");
        emailServerProperties.put("mail.smtp.starttls.enable", "true");

        try {
            // Create an email session using the email server properties set above
            emailSession = Session.getDefaultInstance(emailServerProperties, null);

            /*
            Create a Multi-purpose Internet Mail Extensions (MIME) style email
            message from the MimeMessage class under the email session created.
             */
            htmlEmailMessage = new MimeMessage(emailSession);

            // Set the email TO field to emailTo, which can contain only one email address
            htmlEmailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

            if (emailCc != null && !emailCc.isEmpty()) {
                /*
                Using the setRecipients method (as opposed to addRecipient),
                the CC field is set to contain multiple email addresses
                separated by comma with no spaces in the emailCc string given.
                 */
                htmlEmailMessage.setRecipients(Message.RecipientType.CC, emailCc);
            }
            // It is okay for emailCc to be empty or null since the CC is optional

            // Set the email subject line
            htmlEmailMessage.setSubject(emailSubject);

            // Set the email body to the HTML type text
            htmlEmailMessage.setContent(emailBody, "text/html");

            // Create a transport object that implements the Simple Mail Transfer Protocol (SMTP)
            Transport transport = emailSession.getTransport("smtp");

            /*
            Connect to Gmail's SMTP server using the username and password provided.
            For the Gmail's SMTP server to accept the unsecure connection, the
            Cloud.Software.Email@gmail.com account's "Allow less secure apps" option is set to ON.
             */
            transport.connect("smtp.gmail.com", "Cloud.Software.Email@gmail.com", "csd@VT-1872");

            // Send the htmlEmailMessage created to the specified list of addresses (recipients)
            transport.sendMessage(htmlEmailMessage, htmlEmailMessage.getAllRecipients());

            // Close this service and terminate its connection
            transport.close();

            Methods.showMessage("Information", "Success!", "Email Message is Sent!");

        } catch (AddressException ae) {
            Methods.showMessage("Fatal Error", "Email Address Exception Occurred!",
                    "See: " + ae.getMessage());

        } catch (MessagingException me) {
            Methods.showMessage("Fatal Error",
                    "Email Messaging Exception Occurred! Internet Connection Required!",
                    "See: " + me.getMessage());
        }
    }
    
    public void clearTextMessage() {

        cellPhoneNumber = "";
        cellPhoneCarrierDomain = null;
        mmsTextMessage = "";
    }
    
    public void sendTextMessage(String str, String pin) throws AddressException, MessagingException {

        // Set Email Server Properties
        emailServerProperties = System.getProperties();
        emailServerProperties.put("mail.smtp.port", "587");
        emailServerProperties.put("mail.smtp.auth", "true");
        emailServerProperties.put("mail.smtp.starttls.enable", "true");
        UserFacade fa = this.getUserFacade();
        User u = fa.findByUsername(str);
        cellPhoneNumber = u.getPhoneNumber();
        mmsTextMessage = "Your Authentication Code is: " + pin;
        try {
            // Create an email session using the email server properties set above
            emailSession = Session.getDefaultInstance(emailServerProperties, null);

            /*
            Create a Multi-purpose Internet Mail Extensions (MIME) style email
            message from the MimeMessage class under the email session created.
             */
            mimeEmailMessage = new MimeMessage(emailSession);

            /*
            Specify the email address to send the email message containing the text message as
            
                    5401234567@CellPhoneCarrier's MMS gateway domain
            
            The designated cell phone number will be charged for the text messaging by its carrier.
            Here are the MMS gateway domain names for some of the cell phone carriers and examples:
            
                mms.att.net     for AT&T            5401234567@mms.att.net
                pm.sprint.com   for Sprint          5401234567@pm.sprint.com
                tmomail.net     for T-Mobile        5401234567@tmomail.net
                vzwpix.com      for Verizon         5401234567@vzwpix.com
                vmpix.com       for Virgin Mobile   5401234567@vmpix.com
             */
            mimeEmailMessage.addRecipient(Message.RecipientType.TO, 
                    new InternetAddress(cellPhoneNumber + "@" + cellPhoneCarrierDomain));

            /*
             Since some cell phones may not be able to process text messages in the HTML format,
             send the email message containing the text message in Plain Text format.
             */
            mimeEmailMessage.setContent(mmsTextMessage, "text/plain");

            // Create a transport object that implements the Simple Mail Transfer Protocol (SMTP)
            Transport transport = emailSession.getTransport("smtp");

            /*
            Connect to Gmail's SMTP server using the username and password provided.
            For the Gmail's SMTP server to accept the unsecure connection, the
            Cloud.Software.Email@gmail.com account's "Allow less secure apps" option is set to ON.
             */
            transport.connect("smtp.gmail.com", "Cloud.Software.Email@gmail.com", "csd@VT-1872");

            // Send the email message containing the text message to the specified email address
            transport.sendMessage(mimeEmailMessage, mimeEmailMessage.getAllRecipients());

            // Close this service and terminate its connection
            transport.close();

            Methods.showMessage("Information", "Success!", "MMS Text Message is Sent!");
            clearTextMessage();

        } catch (AddressException ae) {
            Methods.showMessage("Fatal Error", "Email Address Exception Occurred!",
                            "See: " + ae.getMessage());

        } catch (MessagingException me) {
            Methods.showMessage("Fatal Error",
                            "Email Messaging Exception Occurred! Internet Connection Required!",
                            "See: " + me.getMessage());
        }
    }

}
