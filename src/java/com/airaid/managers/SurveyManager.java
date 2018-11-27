/*
 * Created by Osman Balci on 2018.10.20
 * Copyright © 2018 Osman Balci. All rights reserved. 
 */
package com.airaid.managers;

import com.airaid.pojo.Category;
import com.airaid.EntityBeans.User;
import com.airaid.FacadeBeans.UserFacade;
import com.airaid.globals.Methods;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.airaid.controllers.UserTicketController;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.json.JSONArray;

@Named(value = "surveyManager")
@SessionScoped

public class SurveyManager implements Serializable {

    /*
    ==================
    Instance Variables
    ==================
     */
    // Inject (store) the UserFacade object reference into userFacade after it is instantiated at runtime
    @EJB
    private UserFacade userFacade;

    @Inject
    private UserTicketController userSurveyController;

    // 'items' is a List containing the object references of Category objects
    private List<Category> items = null;

    private String category1HowOften;
    private String category1HowMuch;

    private String category2HowOften;
    private String category2HowMuch;

    private String category3HowOften;
    private String category3HowMuch;

    private String category4HowOften;
    private String category4HowMuch;

    private String category5HowOften;
    private String category5HowMuch;

    private String category6HowOften;
    private String category6HowMuch;
    private String category6option;

    private String category7HowOften;
    private String category7HowMuch;

    private String category8HowOften;
    private String category8HowMuch;

    private String category9HowOften;
    private String category9HowMuch;

    private String category10HowOften;
    private String category10HowMuch;

    private String category11HowOften;
    private String category11HowMuch;
    private String category11option;

    private String category12HowOften;
    private String category12HowMuch;
    private String category12option1;
    private String category12option2;
    private String category12option3;

    private String category13HowOften;
    private String category13HowMuch;

    private String category14HowOften;
    private String category14HowMuch;

    private String category15HowOften;
    private String category15HowMuch;

    private String other1HowOften;
    private String other1HowMuch;
    private String other2HowOften;
    private String other2HowMuch;
    private String other3HowOften;
    private String other3HowMuch;
    private String other6HowOften;
    private String other6HowMuch;
    private String other7HowOften;
    private String other7HowMuch;
    private String other13HowOften;
    private String other13HowMuch;
    private String other15HowOften;
    private String other15HowMuch;

    /*
    ==================
    Constructor Method
    ==================
     */
    public SurveyManager() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public UserFacade getUserFacade() {
        return userFacade;
    }

    public UserTicketController getuserSurveyController() {
        return userSurveyController;
    }

    public List<Category> getItems() {
        return items;
    }

    public void setItems(List<Category> items) {
        this.items = items;
    }

    public String getCategory1HowOften() {
        return category1HowOften;
    }

    public void setCategory1HowOften(String category1HowOften) {
        this.category1HowOften = category1HowOften;
    }

    public String getCategory1HowMuch() {
        return category1HowMuch;
    }

    public void setCategory1HowMuch(String category1HowMuch) {
        this.category1HowMuch = category1HowMuch;
    }

    public String getCategory2HowOften() {
        return category2HowOften;
    }

    public void setCategory2HowOften(String category2HowOften) {
        this.category2HowOften = category2HowOften;
    }

    public String getCategory2HowMuch() {
        return category2HowMuch;
    }

    public void setCategory2HowMuch(String category2HowMuch) {
        this.category2HowMuch = category2HowMuch;
    }

    public String getCategory3HowOften() {
        return category3HowOften;
    }

    public void setCategory3HowOften(String category3HowOften) {
        this.category3HowOften = category3HowOften;
    }

    public String getCategory3HowMuch() {
        return category3HowMuch;
    }

    public void setCategory3HowMuch(String category3HowMuch) {
        this.category3HowMuch = category3HowMuch;
    }

    public String getCategory4HowOften() {
        return category4HowOften;
    }

    public void setCategory4HowOften(String category4HowOften) {
        this.category4HowOften = category4HowOften;
    }

    public String getCategory4HowMuch() {
        return category4HowMuch;
    }

    public void setCategory4HowMuch(String category4HowMuch) {
        this.category4HowMuch = category4HowMuch;
    }

    public String getCategory5HowOften() {
        return category5HowOften;
    }

    public void setCategory5HowOften(String category5HowOften) {
        this.category5HowOften = category5HowOften;
    }

    public String getCategory5HowMuch() {
        return category5HowMuch;
    }

    public void setCategory5HowMuch(String category5HowMuch) {
        this.category5HowMuch = category5HowMuch;
    }

    public String getCategory6HowOften() {
        return category6HowOften;
    }

    public void setCategory6HowOften(String category6HowOften) {
        this.category6HowOften = category6HowOften;
    }

    public String getCategory6HowMuch() {
        return category6HowMuch;
    }

    public void setCategory6HowMuch(String category6HowMuch) {
        this.category6HowMuch = category6HowMuch;
    }

    public String getCategory6option() {
        return category6option;
    }

    public void setCategory6option(String category6option) {
        this.category6option = category6option;
    }

    public String getCategory7HowOften() {
        return category7HowOften;
    }

    public void setCategory7HowOften(String category7HowOften) {
        this.category7HowOften = category7HowOften;
    }

    public String getCategory7HowMuch() {
        return category7HowMuch;
    }

    public void setCategory7HowMuch(String category7HowMuch) {
        this.category7HowMuch = category7HowMuch;
    }

    public String getCategory8HowOften() {
        return category8HowOften;
    }

    public void setCategory8HowOften(String category8HowOften) {
        this.category8HowOften = category8HowOften;
    }

    public String getCategory8HowMuch() {
        return category8HowMuch;
    }

    public void setCategory8HowMuch(String category8HowMuch) {
        this.category8HowMuch = category8HowMuch;
    }

    public String getCategory9HowOften() {
        return category9HowOften;
    }

    public void setCategory9HowOften(String category9HowOften) {
        this.category9HowOften = category9HowOften;
    }

    public String getCategory9HowMuch() {
        return category9HowMuch;
    }

    public void setCategory9HowMuch(String category9HowMuch) {
        this.category9HowMuch = category9HowMuch;
    }

    public String getCategory10HowOften() {
        return category10HowOften;
    }

    public void setCategory10HowOften(String category10HowOften) {
        this.category10HowOften = category10HowOften;
    }

    public String getCategory10HowMuch() {
        return category10HowMuch;
    }

    public void setCategory10HowMuch(String category10HowMuch) {
        this.category10HowMuch = category10HowMuch;
    }

    public String getCategory11HowOften() {
        return category11HowOften;
    }

    public void setCategory11HowOften(String category11HowOften) {
        this.category11HowOften = category11HowOften;
    }

    public String getCategory11HowMuch() {
        return category11HowMuch;
    }

    public void setCategory11HowMuch(String category11HowMuch) {
        this.category11HowMuch = category11HowMuch;
    }

    public String getCategory11option() {
        return category11option;
    }

    public void setCategory11option(String category11option) {
        this.category11option = category11option;
    }

    public String getCategory12HowOften() {
        return category12HowOften;
    }

    public void setCategory12HowOften(String category12HowOften) {
        this.category12HowOften = category12HowOften;
    }

    public String getCategory12HowMuch() {
        return category12HowMuch;
    }

    public void setCategory12HowMuch(String category12HowMuch) {
        this.category12HowMuch = category12HowMuch;
    }

    public String getCategory12option1() {
        return category12option1;
    }

    public void setCategory12option1(String category12option1) {
        this.category12option1 = category12option1;
    }

    public String getCategory12option2() {
        return category12option2;
    }

    public void setCategory12option2(String category12option2) {
        this.category12option2 = category12option2;
    }

    public String getCategory12option3() {
        return category12option3;
    }

    public void setCategory12option3(String category12option3) {
        this.category12option3 = category12option3;
    }

    public String getCategory13HowOften() {
        return category13HowOften;
    }

    public void setCategory13HowOften(String category13HowOften) {
        this.category13HowOften = category13HowOften;
    }

    public String getCategory13HowMuch() {
        return category13HowMuch;
    }

    public void setCategory13HowMuch(String category13HowMuch) {
        this.category13HowMuch = category13HowMuch;
    }

    public String getCategory14HowOften() {
        return category14HowOften;
    }

    public void setCategory14HowOften(String category14HowOften) {
        this.category14HowOften = category14HowOften;
    }

    public String getCategory14HowMuch() {
        return category14HowMuch;
    }

    public void setCategory14HowMuch(String category14HowMuch) {
        this.category14HowMuch = category14HowMuch;
    }

    public String getCategory15HowOften() {
        return category15HowOften;
    }

    public void setCategory15HowOften(String category15HowOften) {
        this.category15HowOften = category15HowOften;
    }

    public String getCategory15HowMuch() {
        return category15HowMuch;
    }

    public void setCategory15HowMuch(String category15HowMuch) {
        this.category15HowMuch = category15HowMuch;
    }

    public String getOther1HowOften() {
        return other1HowOften;
    }

    public void setOther1HowOften(String other1HowOften) {
        this.other1HowOften = other1HowOften;
    }

    public String getOther1HowMuch() {
        return other1HowMuch;
    }

    public void setOther1HowMuch(String other1HowMuch) {
        this.other1HowMuch = other1HowMuch;
    }

    public String getOther2HowOften() {
        return other2HowOften;
    }

    public void setOther2HowOften(String other2HowOften) {
        this.other2HowOften = other2HowOften;
    }

    public String getOther2HowMuch() {
        return other2HowMuch;
    }

    public void setOther2HowMuch(String other2HowMuch) {
        this.other2HowMuch = other2HowMuch;
    }

    public String getOther3HowOften() {
        return other3HowOften;
    }

    public void setOther3HowOften(String other3HowOften) {
        this.other3HowOften = other3HowOften;
    }

    public String getOther3HowMuch() {
        return other3HowMuch;
    }

    public void setOther3HowMuch(String other3HowMuch) {
        this.other3HowMuch = other3HowMuch;
    }

    public String getOther6HowOften() {
        return other6HowOften;
    }

    public void setOther6HowOften(String other6HowOften) {
        this.other6HowOften = other6HowOften;
    }

    public String getOther6HowMuch() {
        return other6HowMuch;
    }

    public void setOther6HowMuch(String other6HowMuch) {
        this.other6HowMuch = other6HowMuch;
    }

    public String getOther7HowOften() {
        return other7HowOften;
    }

    public void setOther7HowOften(String other7HowOften) {
        this.other7HowOften = other7HowOften;
    }

    public String getOther7HowMuch() {
        return other7HowMuch;
    }

    public void setOther7HowMuch(String other7HowMuch) {
        this.other7HowMuch = other7HowMuch;
    }

    public String getOther13HowOften() {
        return other13HowOften;
    }

    public void setOther13HowOften(String other13HowOften) {
        this.other13HowOften = other13HowOften;
    }

    public String getOther13HowMuch() {
        return other13HowMuch;
    }

    public void setOther13HowMuch(String other13HowMuch) {
        this.other13HowMuch = other13HowMuch;
    }

    public String getOther15HowOften() {
        return other15HowOften;
    }

    public void setOther15HowOften(String other15HowOften) {
        this.other15HowOften = other15HowOften;
    }

    public String getOther15HowMuch() {
        return other15HowMuch;
    }

    public void setOther15HowMuch(String other15HowMuch) {
        this.other15HowMuch = other15HowMuch;
    }

    /*
    ================
    Instance Methods
    ================

    ***********************************
    Process the Submitted Survey
    ***********************************
     */
    public String processSurvey() {

        // Instantiate a new 'items' List to contain the object references of the Category objects
        items = new ArrayList<>();

        Integer questionNumber;
        String questionTitle;
        String questionAnswer;

        // Format to show only 3 decimal places
        DecimalFormat df = new DecimalFormat("#.###");

        questionNumber = 1;
        questionTitle = "How physically health are you?";
        
        questionAnswer = category1HowMuch;
        Category cat = new Category(questionNumber, questionTitle, questionAnswer);
        items.add(cat);
        
        questionNumber = 2;
        questionTitle = "How important is exercise to you?";
        questionAnswer = category2HowMuch;
        cat = new Category(questionNumber, questionTitle, questionAnswer);
        items.add(cat);
        
        questionNumber = 3;
        questionTitle = "What do you most often do to exercise?";
        questionAnswer = category3HowOften;
        cat = new Category(questionNumber, questionTitle, questionAnswer);
        items.add(cat);
        
        questionNumber = 4;
        questionTitle = "How many times do you exercise per week?";
        questionAnswer = category4HowMuch;
        cat = new Category(questionNumber, questionTitle, questionAnswer);
        items.add(cat);
        
        questionNumber = 5;
        questionTitle = "How often do you drink regular soda or other sugared beverages?";
        questionAnswer = category5HowOften;
        cat = new Category(questionNumber, questionTitle, questionAnswer);
        items.add(cat);

        
        questionNumber = 6;
        questionTitle = "How often do you consume alcohol?";
        questionAnswer = category6HowOften;
        cat = new Category(questionNumber, questionTitle, questionAnswer);
        items.add(cat);
        
        questionNumber = 7;
        questionTitle = "What types of food do you usually snack on?";
        questionAnswer = category7HowOften;
        cat = new Category(questionNumber, questionTitle, questionAnswer);
        items.add(cat);
        
        questionNumber = 8;
        questionTitle = "How many hours of sleep do you get each night on average?";
        questionAnswer = category8HowMuch;
        cat = new Category(questionNumber, questionTitle, questionAnswer);
        items.add(cat);
        
        questionNumber = 9;
        questionTitle = "What is your stress level?";
        questionAnswer = category9HowMuch;
        cat = new Category(questionNumber, questionTitle,questionAnswer);
        items.add(cat);
        
        questionNumber = 10;
        questionTitle = "Which of the following is most effective in coping with your stress?";
        questionAnswer = category10HowOften;
        cat = new Category(questionNumber, questionTitle, questionAnswer);
        items.add(cat);
        /*
        The UserSurvey Table in BevqDB database has the following attributes to set:
            Integer id (The DB Primary Key id value is generated and set at the time of UserSurvey object creation)
            Date    dateEntered
            float   fluidOuncesConsumed
            float   kcalIntake
            String  Survey (Stored as MEDIUMTEXT in BevqDB containing the JSON Array of all BEVQ 15 categories)
            User    userId
         */
        //--------------------------------------
        // Create a new UserSurvey object
        //--------------------------------------
        userSurveyController.prepareCreate();

        //-----------------------
        // Set id attribute value 
        //-----------------------
        /*
        The database Primary Key id value is generated and set at the time of UserSurvey object creation.
         */
        //--------------------------------
        // Set dateEntered attribute value 
        //--------------------------------
        LocalDate localDate = java.time.LocalDate.now();
        Date currentDate = java.sql.Date.valueOf(localDate);

        // Set Date in the format of YYYY-MM-DD

        //----------------------------------
        // Set Survey attribute value 
        //----------------------------------
        /*
        ----------------------------------------------------------------------------------------------
        Convert the List of Category objects into an array of JSON objects as depicted below:
        [
            {
                "howOftenTitle":"3+ times per day",
                "howMuchTitle":"8 fl oz (1 cup)",
                "caloricDensityInKcalPerFluidOunce":0,
                "fluidOuncePerDay":8,
                "categoryNumber":1,
                "optionsOther":"Club Soda, Tonic Water: 2-3 times per week each time 8 fl oz (1 cup)",
                "averageDailyCaloricIntakeInKcal":0,
                "frequencyPerDay":3,
                "categoryName":"Water or Unsweetened Sparkling Water",
                "averageDailyConsumptionInFluidOunce":26.856
            },
            :
            :
        ]
        This conversion calls each Getter method in the Category class to define the KEY:VALUE pair of
        a JSON object for each Category object attribute as shown above. If you include other 
        Getter methods in the Category class, their values will also be included in the JSON file.
        Note that the JSON object {...} lists the Category object attributes in no particular order.
        ----------------------------------------------------------------------------------------------
         */
        JSONArray jasonArray = new JSONArray(items);

        // Convert the JSON array into a String
        String survey = jasonArray.toString();

        // Set the Survey attribute value

        //---------------------------
        // Set userId attribute value 
        //---------------------------
        // Obtain the object reference of the signed-in user
        User signedInUser = (User) Methods.sessionMap().get("user");

        userSurveyController.getSelected().setUserId(signedInUser);

        //----------------------------------------------------------
        // Store the newly created UserSurvey in the database
        //----------------------------------------------------------
        userSurveyController.create();

        //-----------------------------------------------------------
        // Clear the Survey content without displaying message
        //-----------------------------------------------------------
        clearSurvey(false);

        return "/index?faces-redirect=true";
    }

    /*
    ***********************************************************
    Return the Title of How Often Given the User Selected Value
    ***********************************************************
     */
    private String howOftenTitle(String itemValue) {

        String howOftenTitle = "";

        switch (itemValue) {
            case "0":
                howOftenTitle = "Never or < 1 time per week";
                break;
            case "0.143":
                howOftenTitle = "1 time per week";
                break;
            case "0.357":
                howOftenTitle = "2-3 times per week";
                break;
            case "0.714":
                howOftenTitle = "4-6 times per week";
                break;
            case "1":
                howOftenTitle = "1 time per day";
                break;
            case "2":
                howOftenTitle = "2 times per day";
                break;
            case "3":
                howOftenTitle = "3+ times per day";
                break;
            default:
                System.out.print("howOftenTitle itemValue is out of range!");
                break;
        }

        return howOftenTitle;
    }

    /*
    **********************************************************
    Return the Title of How Much Given the User Selected Value
    **********************************************************
     */
    private String howMuchTitle(String itemValue) {

        String howMuchTitle = "";

        switch (itemValue) {
            case "0":
                howMuchTitle = "I do not drink it";
                break;
            case "4":
                howMuchTitle = "&lt; 6 fl oz (&frac34; cup)";
                break;
            case "8":
                howMuchTitle = "8 fl oz (1 cup)";
                break;
            case "12":
                howMuchTitle = "12 fl oz (1&frac12; cups)";
                break;
            case "16":
                howMuchTitle = "16 fl oz (2 cups)";
                break;
            case "20":
                howMuchTitle = "20 fl oz (2&frac12; cups)";
                break;
            case "24":
                howMuchTitle = "24 fl oz (3 cups)";
                break;
            case "28":
                howMuchTitle = "28 fl oz (3&frac12; cups)";
                break;
            case "32":
                howMuchTitle = "32 fl oz (4 cups)";
                break;
            default:
                System.out.print("howMuchTitle itemValue is out of range!");
                break;
        }

        return howMuchTitle;
    }

    /*
    ************************************************
    Clear All of the Selections in the Survey
    ************************************************
     */
    public void clearSurvey(Boolean displayMessage) {

        items = null;

        category1HowOften = null;
        category1HowMuch = null;
        category2HowOften = null;
        category2HowMuch = null;
        category3HowOften = null;
        category3HowMuch = null;
        category4HowOften = null;
        category4HowMuch = null;
        category5HowOften = null;
        category5HowMuch = null;

        category6HowOften = null;
        category6HowMuch = null;
        category6option = null;

        category7HowOften = null;
        category7HowMuch = null;
        category8HowOften = null;
        category8HowMuch = null;
        category9HowOften = null;
        category9HowMuch = null;
        category10HowOften = null;
        category10HowMuch = null;

        category11HowOften = null;
        category11HowMuch = null;
        category11option = null;

        category12HowOften = null;
        category12HowMuch = null;
        category12option1 = null;
        category12option2 = null;
        category12option3 = null;

        category13HowOften = null;
        category13HowMuch = null;
        category14HowOften = null;
        category14HowMuch = null;
        category15HowOften = null;
        category15HowMuch = null;

        other1HowOften = null;
        other1HowMuch = null;
        other2HowOften = null;
        other2HowMuch = null;
        other3HowOften = null;
        other3HowMuch = null;
        other6HowOften = null;
        other6HowMuch = null;
        other7HowOften = null;
        other7HowMuch = null;
        other13HowOften = null;
        other13HowMuch = null;
        other15HowOften = null;
        other15HowMuch = null;

        if (displayMessage) {
            Methods.showMessage("Information", "Cleared!", "All Selections are Reset!");
        }
    }
    
    public void generateSurveysReport()
    {
        
    }

    /*
    *******************************************
    Pre-process the PDF File to be in Landscape 
    Orientation on Letter Size Paper
    *******************************************
     */
    public void preProcessPDF(Object document) {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER.rotate());
        pdf.open();
    }

    /*
    *****************************************
    Warn the User for 5 Minutes of Inactivity
    *****************************************
     */
    public void onIdle() {
        Methods.showMessage("Warning", "No User Action for the Last 5 Minutes!", "Do You Need More Time?");
    }

    /*
    ***************************************************
    Welcome Back the User After 5 Minutes of Inactivity
    ***************************************************
     */
    public void onActive() {
        Methods.showMessage("Warning", "Welcome Back!", "Please Continue Filling Out Your Survey!");
    }

}
