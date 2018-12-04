/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airaid.controllers;

import com.airaid.EntityBeans.User;
import com.airaid.pojo.Flight;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Andrew
 */
@Named("directionController")
@RequestScoped
public class DirectionController {
    
    
    public String getTravelTime(User user, Flight flight) {
        return "0 Minutes";
    }
}
