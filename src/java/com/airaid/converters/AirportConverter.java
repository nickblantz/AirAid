/*
 * Created by Nicholas Blantz on 2018.12.02  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.converters;

import com.airaid.controllers.FlightSearchController;
import com.airaid.pojo.Airport;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
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
    
    
    // Converts specified string to Airport
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            for (Airport airport : fsc.getAirports()) {
                if (value.equals(airport.getName()) || value.equals(airport.getIata())) {
                    return airport;
                }
            }
        }
        return null;
    }

    // Converts Airport to search string
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
