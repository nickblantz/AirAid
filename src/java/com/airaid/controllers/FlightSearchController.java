/*
 * Created by Nicholas Blantz on 2018.11.28  * 
 * Copyright © 2018 Nicholas Blantz. All rights reserved. * 
 */
package com.airaid.controllers;

import com.airaid.EntityBeans.User;
import com.airaid.EntityBeans.UserTicket;
import com.airaid.FacadeBeans.UserTicketFacade;
import com.airaid.globals.Methods;
import com.airaid.pojo.Airport;
import com.airaid.pojo.Flight;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
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
    private Flight selected;
    @EJB
    private UserTicketFacade userTicketFacade;

    private final String apiKey = "6bd961-bbc2d3";
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

    public UserTicketFacade getUserTicketFacade() {
        return userTicketFacade;
    }

    public void setUserTicketFacade(UserTicketFacade userTicketFacade) {
        this.userTicketFacade = userTicketFacade;
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

    public Flight getSelected() {
        return selected;
    }

    public void setSelected(Flight selected) {
        this.selected = selected;
    }

    public String performSearch() {
        String flightAPIEndpoint = apiFlightEndpoint + "&iataCode=" + source.getIata() + "&type=departure";
        try {
            searchedItems = new ArrayList<>();
            String jsonData = readUrlContent(flightAPIEndpoint);
            JSONArray flightArray = new JSONArray(jsonData);
            for (int i = 0; i < flightArray.length(); i++) {
                try {
                    JSONObject flightData = flightArray.getJSONObject(i);

                    // Creates Joda DateTime objects to find difference
                    DateTimeFormatter parser = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                    DateTime edcJ = parser.parseDateTime(flightData.getJSONObject("departure").getString("scheduledTime"));
                    DateTime eadJ = parser.parseDateTime(flightData.getJSONObject("arrival").getString("scheduledTime"));
                    DateTime fligtDateJ = new DateTime(flightDate);
                    int dayDiff = Days.daysBetween(edcJ, fligtDateJ).getDays() + 1;

                    // Mocking Data
                    Date expectedDepartureDate = edcJ.plusDays(dayDiff).toDate();
                    Date expectedArrivalDate = eadJ.plusDays(dayDiff).toDate();
                    
                    int price;
                    Random ran = new Random();
                    price = ran.nextInt(243) + 226; //prices aren't given by API, so we make up a price between 226-468

                    String flightDestIaca = flightData.getJSONObject("arrival").getString("iataCode");
                    if (flightData.getString("status").equals("scheduled") && flightDestIaca.equals(destination.getIata())) {
                        searchedItems.add(new Flight(flightData.getJSONObject("flight").getString("iataNumber"),
                                source, destination,
                                flightData.getJSONObject("airline").getString("name"),
                                expectedDepartureDate,
                                expectedArrivalDate,
                                (double) price));
                    }
                    } catch (JSONException e) {
                        // Do Nothing
                    }
                }
            

            // Creating mock flight for testing purposes
            createMockTestingFlight();

        } catch (Exception ex) {
            Methods.showMessage("Fatal Error", "Error in processing JSON data returned from Airport API!",
                    "See: " + ex.getMessage());
        }
        return "/flightSearch/Results?faces-redirect=true";
    }

    private void createMockTestingFlight() {
        DateTime curDateJ = new DateTime(new Date());
        Date curDep = curDateJ.plusMinutes(1).toDate();
        Date curArr = curDateJ.plusHours(2).toDate();
        
        int price;
        Random ran = new Random();
        price = ran.nextInt(243) + 226; //prices aren't given by API, so we make up a price between 226-468
                    
        searchedItems.add(new Flight("Test1234", source, destination, "AirAid - Testing", curDep, curArr, (double) price));
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
        } catch (JSONException ex) {
            // Do Nothing
        } catch (Exception ex) {
            Methods.showMessage("Fatal Error", "Error in processing JSON data returned from Airport API!",
                    "See: " + ex.getMessage());
        }

    }

    public void purchaseTicket(Flight input) {
        User user = (User) Methods.sessionMap().get("user");
        UserTicket newTicket = new UserTicket(input.getExpectedDepartureDate(), input.getExpectedArrivalDate(), input.getSource().getName(), input.getDestination().getName(), input.getSource().getLongitude(), input.getSource().getLatitude(), input.getDestination().getName(), input.getAirline(), input.getPrice().floatValue(), user);
        userTicketFacade.create(newTicket);
    }
    
    public void goToDirections () throws IOException {
        System.out.println("goToDirections() Called");
        User signedInUser = (User) Methods.sessionMap().get("user");
        if (signedInUser != null && selected != null) {
            String destAddr = selected.getSource().getLatitude() + "," + selected.getSource().getLongitude();
            String srcAddr = signedInUser.getAddress1();
            srcAddr = srcAddr.replace(" ", "+");
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            
            externalContext.redirect("http://maps.google.com/maps?saddr=" + srcAddr + "&daddr=" + destAddr);
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
