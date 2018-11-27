/*
 * Created by Osman Balci on 2018.10.14
 * Copyright © 2018 Osman Balci. All rights reserved.
 */
package com.airaid.pojo;

public class Category {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    Integer questionNumber;
    String questionTitle;
    String questionAnswer;

    
    public Category(Integer questionNum, String questionName, String answer)
    {
        this.questionNumber = questionNum;
        this.questionTitle = questionName;
        this.questionAnswer = answer;
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
    

}
