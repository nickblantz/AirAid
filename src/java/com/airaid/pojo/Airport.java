/*
 * Created by Nicholas Blantz on 2018.12.02  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.pojo;

import java.io.Serializable;

/**
 * Represents an airport
 * @author Admin
 */
public class Airport implements Serializable {
    private int id;
    private String name;
    private String iata;
    private double longitude;
    private double latitude;
    
    public Airport(int id, String name, String iata, double longitude, double latitude) {
        this.id = id;
        this.name = name;
        this.iata = iata;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
   
    
}
