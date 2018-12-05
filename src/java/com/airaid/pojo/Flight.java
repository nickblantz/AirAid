/*
 * Created by Nicholas Blantz on 2018.12.02  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a flight between a source and a destination airport
 * @author Admin
 */
public class Flight implements Serializable {
    private String flightIata;
    private Airport source, destination;
    private String airline;
    private Date expectedDepartureDate, expectedArrivalDate;
    private Double price;
    
    public Flight(String flightIata, Airport source, Airport destination, String airline, Date expectedDepartureDate, Date expectedArrivalDate, Double price) {
        this.flightIata = flightIata;
        this.source = source;
        this.destination = destination;
        this.airline = airline;
        this.expectedDepartureDate = expectedDepartureDate;
        this.expectedArrivalDate = expectedArrivalDate;
        this.price = price;
    }
    
    public String getFlightIata() {
        return flightIata;
    }

    public void setFlightIata(String flightIata) {
        this.flightIata = flightIata;
    }

    public Airport getSource() {
        return source;
    }

    public void setSource(Airport source) {
        this.source = source;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }
    
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Date getExpectedDepartureDate() {
        return expectedDepartureDate;
    }

    public void setExpectedDepartureDate(Date expectedDepartureDate) {
        this.expectedDepartureDate = expectedDepartureDate;
    }
    
    public Date getExpectedArrivalDate() {
        return expectedArrivalDate;
    }

    public void setExpectedArrivalDate(Date expectedArrivalDate) {
        this.expectedArrivalDate = expectedArrivalDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String toString() {
        return source.getName() + "-" + destination.getName() + " (" + expectedDepartureDate.toString() + ")";
    }
}
