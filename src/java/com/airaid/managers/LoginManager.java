/*
 * Created by Osman Balci on 2018.06.11
 * Copyright © 2018 Osman Balci. All rights reserved.
 */
package com.airaid.managers;

import com.airaid.globals.Password;
import com.airaid.EntityBeans.User;
import com.airaid.FacadeBeans.UserFacade;
import com.airaid.controllers.EmailController;
import com.airaid.globals.Methods;

import java.io.Serializable;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

@Named(value = "loginManager")
@SessionScoped

public class LoginManager implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private String username;
    private String password;
    private boolean admin;
    private String pin;
    private String pinAnswer;

    /*
    The instance variable 'userFacade' is annotated with the @EJB annotation.
    The @EJB annotation directs the EJB Container (of the GlassFish AS) to inject (store) the object reference
    of the UserFacade object, after it is instantiated at runtime, into the instance variable 'userFacade'.
     */
    @EJB
    private UserFacade userFacade;

    @Inject 
    private EmailController emailController;
    /*
    ==================
    Constructor Method
    ==================
     */
    public LoginManager() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    
    

    public void setPassword(String password) {
        this.password = password;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    /*
    ================
    Instance Methods
    ================

    *****************************************************
    Sign in the User if the Entered Username and Password
    are Valid and Redirect to Show the Profile Page
    *****************************************************
     */
    public Boolean loginUser() {

        // Since we will redirect to show the Profile page, invoke preserveMessages()
        Methods.preserveMessages();

        String enteredUsername = getUsername();

        // Obtain the object reference of the User object from the entered username
        User user = getUserFacade().findByUsername(enteredUsername);

        if (user == null) {
            Methods.showMessage("Fatal Error", "Unknown Username!",
                    "Entered username " + enteredUsername + " does not exist!");
            return false;

        } 
        else {
            String actualUsername = user.getUsername();

            String actualPassword = user.getPassword();
            String enteredPassword = getPassword();

            if (!actualUsername.equals(enteredUsername)) {
                Methods.showMessage("Fatal Error", "Invalid Username!", "Entered Username is Unknown!");
                return false;
            }

            //------------------------------------------------------------------------------------
            // Obtain the user's password String containing the following parts from the database
            //       "algorithmName":"PBKDF2_ITERATIONS":"hashSize":"salt":"hash",
            // extract the actual password from the parts, and compare it with the password String
            // entered by the user by using Key Stretching to prevent brute-force attacks.
            //------------------------------------------------------------------------------------            
            try {
                if (Password.verifyPassword(enteredPassword, actualPassword)) {
                    // entered password = user's actual password
                } else {
                    Methods.showMessage("Fatal Error", "Invalid Password!", "Please Enter a Valid Password!");
                    return false;
                }
            } catch (Password.CannotPerformOperationException | Password.InvalidHashException ex) {

                Methods.showMessage("Fatal Error", "Password Manager was unable to perform its operation!",
                        "See: " + ex.getMessage());
                return false;
            }

            // Redirect to show the Profile page
            if (user.getUsername().equals("Administrator"))
            {
                return true;
            }
            this.getUserFacade().edit(user);
            return true;
        }
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
        pinAnswer = str.toString();
        return str.toString();
    }
    
    
    public String redirectVerify() throws MessagingException
    {
        boolean loggedIn = this.loginUser();
        User user = getUserFacade().findByUsername(this.getUsername());
        
        if (!loggedIn)
        {
            return "";
        }
        
        emailController.sendEmail(this.getUsername(), this.getRandomString());
        if (user.getIsVerified())
        {
            return "/userAccount/VerifyText.xhtml?faces-redirect=true";
        }
        return "/userAccount/VerifyEmail.xhtml?faces-redirect=true";
    }
    
    public String verify(String str)
    {
        //TODO Remove Bypass
        if (pin.equals(pinAnswer) || pin.equals("Admin"))
        {
            User user = this.getUserFacade().findByUsername(str);
            user.setIsVerified(true);
            // Initialize the session map with user properties of interest
            initializeSessionMap(user);
            if (user.getUsername().equals("Administrator")) {
                return "/userAccount/AdminProfile.xhtml?faces-redirect=true";
            }
            else 
            {
                return "/userAccount/Profile.xhtml?faces-redirect=true";
            }
        }
        else
        {
            Methods.preserveMessages();
            Methods.showMessage("Error", "Incorrect Verification Pin!", "");
            return "/userAccount/VerifyEmail.xhtml?faces-redirect=true";
        }
    }

    /*
    ******************************************************************
    Initialize the Session Map to Hold Session Attributes of Interests 
    ******************************************************************
     */
    public void initializeSessionMap(User user) {
        // Store the object reference of the signed-in user
        Methods.sessionMap().put("user", user);

        // Store the First Name of the signed-in user
        Methods.sessionMap().put("first_name", user.getFirstName());

        // Store the Last Name of the signed-in user
        Methods.sessionMap().put("last_name", user.getLastName());

        // Store the Username of the signed-in user
        Methods.sessionMap().put("username", username);

        // Store signed-in user's Primary Key in the database
        Methods.sessionMap().put("user_id", user.getId());
    }

}
