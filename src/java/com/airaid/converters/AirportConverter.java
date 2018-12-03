/*
 * Created by Nicholas Blantz on 2018.12.02  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.converters;

import com.airaid.controllers.FlightSearchController;
import com.airaid.pojo.Airport;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Admin
 */
@RequestScoped
@Named("airportConverter")
public class AirportConverter implements Converter, Serializable {

    @Inject
    private FlightSearchController fsc;
    
    public FlightSearchController getFsc() {
        return fsc;
    }
    
    public void setFsc(FlightSearchController fsc) {
        this.fsc = fsc;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            System.out.println("Null check for fsc: [" + (fsc == null) + "]");
            for (Airport airport : fsc.getAirports()) {
                if (value.equals(airport.getName()) || value.equals(airport.getIata())) {
                    System.out.println("Found airport: [value: " + value + "]");
                    return airport;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if(object != null) {
            return ((Airport) object).getName();
        }
        else {
            return null;
        }
    }
    
}
