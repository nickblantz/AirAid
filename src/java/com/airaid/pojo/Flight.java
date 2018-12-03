/*
 * Created by Nicholas Blantz on 2018.12.02  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.pojo;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Flight {
    private String sourceName, destinationName, sourceIata, destinationIata, airline;
    private Date expectedDepartureDate, expectedArrivalDate;
    private Double price;
    
    public Flight(String sourceName, String destinationName, String sourceIata, String destinationIata, String airline, Date expectedDepartureDate, Date expectedArrivalDate, Double price) {
        this.sourceName = sourceName;
        this.destinationName = destinationName;
        this.sourceIata = sourceIata;
        this.destinationIata = destinationIata;
        this.airline = airline;
        this.expectedDepartureDate = expectedDepartureDate;
        this.expectedArrivalDate = expectedArrivalDate;
        this.price = price;
    }
    
    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getSourceIata() {
        return sourceIata;
    }

    public void setSourceIata(String sourceIata) {
        this.sourceIata = sourceIata;
    }

    public String getDestinationIata() {
        return destinationIata;
    }

    public void setDestinationIata(String destinationIata) {
        this.destinationIata = destinationIata;
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
}
