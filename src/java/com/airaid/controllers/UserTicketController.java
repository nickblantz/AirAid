package com.airaid.controllers;

import com.airaid.EntityBeans.User;
import com.airaid.EntityBeans.UserTicket;
import com.airaid.controllers.util.JsfUtil;
import com.airaid.controllers.util.JsfUtil.PersistAction;
import com.airaid.FacadeBeans.UserTicketFacade;
import com.airaid.globals.Methods;
import java.io.IOException;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("userTicketController")
@SessionScoped
public class UserTicketController implements Serializable {

    @EJB
    private com.airaid.FacadeBeans.UserTicketFacade ejbFacade;
    private ArrayList<UserTicket> available = new ArrayList();
    private List<UserTicket> items = null;
    private List<UserTicket> userItems = null;
    

    private UserTicket selected;

    public UserTicketController() {
    }

    public UserTicket getSelected() {
        return selected;
    }

    public void setSelected(UserTicket selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserTicketFacade getFacade() {
        return ejbFacade;
    }

    public UserTicket prepareCreate() {
        selected = new UserTicket();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserTicketCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserTicketUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserTicketDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UserTicket> getItems() {
        if (items == null) {
        items = getFacade().findAll();
        }
        return items;
    }

    public List<UserTicket> getUsersItems() {
        items = getFacade().findAll();
        User signedInUser = (User) Methods.sessionMap().get("user");
        userItems = new ArrayList();
        items.forEach(entry -> {
            if (entry.getUserId() != null){
                if (entry.getUserId().getId() == signedInUser.getId())
                {
                    userItems.add(entry);
                }
            }
        });
        
        return userItems;
    }
    
    public void goToDirections () throws IOException {
        User signedInUser = (User) Methods.sessionMap().get("user");
        if (signedInUser != null && selected != null) {
            String destination = selected.getSrcStreet();
            destination = destination.replace(" ", "+");
            String source = signedInUser.getAddress1();
            source = source.replace(" ", "+");
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect("http://maps.google.com/maps?saddr=" + source + "&daddr=" + destination);
        }
    }
    
    public List<UserTicket> getFreeItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        available.clear();
        items.forEach(item -> {
            if (item.getUserId() == null) {
                available.add(item);
            }
        });
        return available;
    }
    
    public List<UserTicket> purchasedTickets() {
        if (items == null) {
            items = getFacade().findAll();
        }
        available.clear();
        items.forEach(item -> {
            if (item.getUserId() != null) {
                available.add(item);
            }
        });
        return available;
    }
    
    public String purchaseTicket()
    {
        User signedInUser = (User) Methods.sessionMap().get("user");
        selected.setUserId(signedInUser);
        
        /* perist so the change is updated in the database */
        persist(PersistAction.UPDATE, "");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The UPDATE (EDIT) operation is successfully performed.
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
        return "/userTicket/List.xhtml?faces-redirect=true";
    }
    
    public String refundTicket()
    {
        
        /* perist so the change is updated in the database */
        persist(PersistAction.DELETE, "");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The UPDATE (EDIT) operation is successfully performed.
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
        return "/userTicket/List.xhtml?faces-redirect=true";
    }
    
    public String adminRefundTicket()
    {
        
        /* perist so the change is updated in the database */
        persist(PersistAction.DELETE, "");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The UPDATE (EDIT) operation is successfully performed.
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
        return "/adminTickets/List.xhtml?faces-redirect=true";
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UserTicket getUserTicket(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<UserTicket> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UserTicket> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UserTicket.class)
    public static class UserTicketControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserTicketController controller = (UserTicketController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userTicketController");
            return controller.getUserTicket(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UserTicket) {
                UserTicket o = (UserTicket) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UserTicket.class.getName()});
                return null;
            }
        }

    }

}
