/*
 * Created by Nicholas Blantz on 2018.11.28  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.controllers;

import com.airaid.EntityBeans.UserTicket;
import com.airaid.FacadeBeans.UserTicketFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Admin
 */
@Named("flightSearchController")
@SessionScoped
public class FlightSearchController implements Serializable{
    private String source, destination;
    private Date flightDate;
    private List<UserTicket> searchedItems;
    private UserTicket selected;
    @EJB private UserTicketFacade userTicketFacade;
    
    public FlightSearchController() {}
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public List<UserTicket> getSearchedItems() {
        return searchedItems;
    }

    public void setSearchedItems(List<UserTicket> searchedItems) {
        this.searchedItems = searchedItems;
    }

    public UserTicket getSelected() {
        return selected;
    }

    public void setSelected(UserTicket selected) {
        this.selected = selected;
    }
    
    public String performSearch() {
        searchedItems = userTicketFacade.searchQuery(source, destination, flightDate);
        return "/flightSearch/Results?faces-redirect=true";
    }
}
