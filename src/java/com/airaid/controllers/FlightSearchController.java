/*
 * Created by Nicholas Blantz on 2018.11.28  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.controllers;

import com.airaid.EntityBeans.UserTicket;
import com.airaid.FacadeBeans.UserTicketFacade;
import com.airaid.globals.Methods;
import com.airaid.pojo.Airport;
import com.airaid.pojo.Flight;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Admin
 */
@Named("flightSearchController")
@SessionScoped
public class FlightSearchController implements Serializable {

    private Airport source, destination;
    private Date flightDate;
    private List<Airport> airports;
    private List<Flight> searchedItems;
    private UserTicket selected;

    private final String apiKey = "0ed98c-7172ae";
    private final String apiAirportEndpoint = "https://aviation-edge.com/v2/public/airportDatabase?key=" + apiKey + "&codeIso2Country=US";
    private final String apiFlightEndpoint = "https://aviation-edge.com/v2/public/timetable?key=" + apiKey;

    public FlightSearchController() {
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

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public List<Flight> getSearchedItems() {
        return searchedItems;
    }

    public void setSearchedItems(List<Flight> searchedItems) {
        this.searchedItems = searchedItems;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public UserTicket getSelected() {
        return selected;
    }

    public void setSelected(UserTicket selected) {
        this.selected = selected;
    }

    public String performSearch() {
        String flightAPIEndpoint = apiFlightEndpoint + "&iataCode=" + source.getIata() + "&type=departure";
        try {
            searchedItems = new ArrayList<>();
            String jsonData = readUrlContent(flightAPIEndpoint);
            JSONArray flightArray = new JSONArray(jsonData);

            for (int i = 0; i < flightArray.length(); i++) {
                JSONObject flightData = flightArray.getJSONObject(i);
                System.out.println(flightData.toString());
                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                Date expectedDepartureDate = parser.parse(flightData.getJSONObject("departure").getString("scheduledTime"));
                String flightDestIaca = flightData.getJSONObject("arrival").getString("iataCode");
                Date startDate = flightDate;
                Date endDate = new Date(flightDate.getTime() + 1 * 24 * 60 * 60 * 1000);
                System.out.println(flightData.getString("status") + " == scheduled && " + flightDestIaca + " == " + destination.getIata());
                if (flightData.getString("status").equals("scheduled")
                        && flightDestIaca.equals(destination.getIata()) && 
                        startDate.compareTo(expectedDepartureDate) * expectedDepartureDate.compareTo(endDate) >= 0) {
                    searchedItems.add(new Flight(source.getName(), destination.getName(),
                            source.getIata(), destination.getIata(),
                            flightData.getJSONObject("airline").getString("name"),
                            parser.parse(flightData.getJSONObject("departure").getString("scheduledTime")),
                            parser.parse(flightData.getJSONObject("arrival").getString("scheduledTime")),
                            0.00));
                }
            }

        } catch (Exception ex) {
            Methods.showMessage("Fatal Error", "Error in processing JSON data returned from Airport API!",
                    "See: " + ex.getMessage());
        }
        return "/flightSearch/Results?faces-redirect=true";
    }

    @PostConstruct
    public void initAirports() {
        try {
            airports = new ArrayList<>();
            String jsonData = readUrlContent(apiAirportEndpoint);
            JSONArray airportArray = new JSONArray(jsonData);

            for (int i = 0; i < airportArray.length(); i++) {
                JSONObject airportData = airportArray.getJSONObject(i);
                airports.add(new Airport(
                        airportData.getInt("airportId"),
                        airportData.getString("nameAirport"),
                        airportData.getString("codeIataAirport"),
                        airportData.getDouble("longitudeAirport"),
                        airportData.getDouble("latitudeAirport")));
            }

        } catch (Exception ex) {
            Methods.showMessage("Fatal Error", "Error in processing JSON data returned from Airport API!",
                    "See: " + ex.getMessage());
        }

    }

    public String readUrlContent(String webServiceURL) throws Exception {
        /*
        reader is an object reference pointing to an object instantiated from the BufferedReader class.
        Currently, it is "null" pointing to nothing.
         */
        BufferedReader reader = null;

        try {
            // Create a URL object from the webServiceURL given
            URL url = new URL(webServiceURL);
            /*
            The BufferedReader class reads text from a character-input stream, buffering characters
            so as to provide for the efficient reading of characters, arrays, and lines.
             */
            reader = new BufferedReader(new InputStreamReader(url.openStream()));

            // Create a mutable sequence of characters and store its object reference into buffer
            StringBuilder buffer = new StringBuilder();

            // Create an array of characters of size 10240
            char[] chars = new char[10240];

            int numberOfCharactersRead;
            /*
            The read(chars) method of the reader object instantiated from the BufferedReader class
            reads 10240 characters as defined by "chars" into a portion of a buffered array.

            The read(chars) method attempts to read as many characters as possible by repeatedly
            invoking the read method of the underlying stream. This iterated read continues until
            one of the following conditions becomes true:

                (1) The specified number of characters have been read, thus returning the number of characters read.
                (2) The read method of the underlying stream returns -1, indicating end-of-file, or
                (3) The ready method of the underlying stream returns false, indicating that further input requests would block.

            If the first read on the underlying stream returns -1 to indicate end-of-file then the read(chars) method returns -1.
            Otherwise the read(chars) method returns the number of characters actually read.
             */
            while ((numberOfCharactersRead = reader.read(chars)) != -1) {
                buffer.append(chars, 0, numberOfCharactersRead);
            }

            // Return the String representation of the created buffer
            return buffer.toString();

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public List<Airport> completeText(String query) {
        List<Airport> results = new ArrayList<>();
        for (Airport airport : airports) {
            if (airport.getName().contains(query)) {
                results.add(airport);
            }
        }
        return results;
    }
}
