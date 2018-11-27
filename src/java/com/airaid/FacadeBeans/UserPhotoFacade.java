/*
 * Created by Viet Doan on 2018.11.27  * 
 * Copyright © 2018 Viet Doan. All rights reserved. * 
 */
package com.airaid.FacadeBeans;

import com.airaid.EntityBeans.UserPhoto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.airaid.EntityBeans.User;

/**
 *
 * @author VDoan
 */
@Stateless
public class UserPhotoFacade extends AbstractFacade<UserPhoto> {

    @PersistenceContext(unitName = "AirAidPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserPhotoFacade() {
        super(UserPhoto.class);
    }
    
    public List<UserPhoto> findPhotosByUserPrimaryKey(Integer primaryKey) {

        return (List<UserPhoto>) em.createNamedQuery("UserPhoto.findPhotosByUserDatabasePrimaryKey")
                .setParameter("primaryKey", primaryKey)
                .getResultList();
    }
    
    public List<UserPhoto> findPhotosByUser(User primaryKey)
    {
        return (List<UserPhoto>) em.createNamedQuery("UserPhoto.findPhotosByUserKey")
                .setParameter("primaryKey", primaryKey)
                .getResultList();
    }
    
}