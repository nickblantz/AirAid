/*
 * Created by Viet Doan on 2018.11.27  * 
 * Copyright © 2018 Viet Doan. All rights reserved. * 
 */
package com.airaid.FacadeBeans;

import com.airaid.EntityBeans.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author VDoan
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "AirAidPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findByUsername(String username) {
        if (em.createQuery("SELECT c FROM User c WHERE c.username = :username")
                .setParameter("username", username)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (User) (em.createQuery("SELECT c FROM User c WHERE c.username = :username")
                    .setParameter("username", username)
                    .getSingleResult());
        }
    }

    /**
     * Deletes the User entity whose primary key is id
     * @param id is the Primary Key of the User entity in a table row in the database.
     */
    public void deleteUser(int id) {
        
        // The find method is inherited from the parent AbstractFacade class
        User user = em.find(User.class, id);
        
        // The remove method is inherited from the parent AbstractFacade class
        em.remove(user); 
    }
    
}