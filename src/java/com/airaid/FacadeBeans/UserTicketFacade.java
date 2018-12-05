/*
 * Created by Viet Doan on 2018.11.27  * 
 * Copyright © 2018 Viet Doan. All rights reserved. * 
 */
package com.airaid.FacadeBeans;

import com.airaid.EntityBeans.UserTicket;
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
}
