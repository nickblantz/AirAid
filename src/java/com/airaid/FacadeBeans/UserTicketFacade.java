/*
 * Created by Viet Doan on 2018.11.27  * 
 * Copyright © 2018 Viet Doan. All rights reserved. * 
 */
package com.airaid.FacadeBeans;

import com.airaid.EntityBeans.UserTicket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author VDoan
 */
@Stateless
public class UserTicketFacade extends AbstractFacade<UserTicket> {

    @PersistenceContext(unitName = "AirAidPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTicketFacade() {
        super(UserTicket.class);
    }

    public List<UserTicket> searchQuery(String source, String destination, Date flightDate) {
        // Place the % wildcard before and after the search string to search for it anywhere in the company name 
        Calendar dateModifier = new GregorianCalendar();
        dateModifier.setTime(flightDate);
        // reset hour, minutes, seconds and millis
        dateModifier.set(Calendar.HOUR_OF_DAY, 0);
        dateModifier.set(Calendar.MINUTE, 0);
        dateModifier.set(Calendar.SECOND, 0);
        dateModifier.set(Calendar.MILLISECOND, 0);
        Date startDate = dateModifier.getTime();
        dateModifier.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = dateModifier.getTime();
        System.out.println("Source: " + source + " Destination: " + destination + " Start Date: " + startDate.toString() + " End Date: " + endDate.toString());
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT ut FROM UserTicket ut WHERE ut.userId IS null AND ut.srcName = :srcName AND ut.destName = :destName AND ut.departureTime BETWEEN :startDate AND :endDate")
                .setParameter("srcName", source)
                .setParameter("destName", destination)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }

}
