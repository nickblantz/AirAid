/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airaid.EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrew
 */
@Entity
@Table(name = "UserTicket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTicket.findAll", query = "SELECT u FROM UserTicket u")
    , @NamedQuery(name = "UserTicket.findById", query = "SELECT u FROM UserTicket u WHERE u.id = :id")
    , @NamedQuery(name = "UserTicket.findByDepartureTime", query = "SELECT u FROM UserTicket u WHERE u.departureTime = :departureTime")
    , @NamedQuery(name = "UserTicket.findByArrivalTime", query = "SELECT u FROM UserTicket u WHERE u.arrivalTime = :arrivalTime")
    , @NamedQuery(name = "UserTicket.findBySrcName", query = "SELECT u FROM UserTicket u WHERE u.srcName = :srcName")
    , @NamedQuery(name = "UserTicket.findByDestName", query = "SELECT u FROM UserTicket u WHERE u.destName = :destName")
    , @NamedQuery(name = "UserTicket.findBySrcLongitude", query = "SELECT u FROM UserTicket u WHERE u.srcLongitude = :srcLongitude")
    , @NamedQuery(name = "UserTicket.findBySrcLatitude", query = "SELECT u FROM UserTicket u WHERE u.srcLatitude = :srcLatitude")
    , @NamedQuery(name = "UserTicket.findBySrcStreet", query = "SELECT u FROM UserTicket u WHERE u.srcStreet = :srcStreet")
    , @NamedQuery(name = "UserTicket.findByAirline", query = "SELECT u FROM UserTicket u WHERE u.airline = :airline")
    , @NamedQuery(name = "UserTicket.findByPrice", query = "SELECT u FROM UserTicket u WHERE u.price = :price")})
public class UserTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "src_name")
    private String srcName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "dest_name")
    private String destName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "src_longitude")
    private double srcLongitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "src_latitude")
    private double srcLatitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "src_street")
    private String srcStreet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "airline")
    private String airline;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public UserTicket() {
    }

    public UserTicket(Integer id) {
        this.id = id;
    }

    public UserTicket(Integer id, Date departureTime, Date arrivalTime, String srcName, String destName, double srcLongitude, double srcLatitude, String srcStreet, String airline, float price, User user) {
        this.id = id;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.srcName = srcName;
        this.destName = destName;
        this.srcLongitude = srcLongitude;
        this.srcLatitude = srcLatitude;
        this.srcStreet = srcStreet;
        this.airline = airline;
        this.price = price;
        this.userId = user;
    }
    
    public UserTicket(Date departureTime, Date arrivalTime, String srcName, String destName, double srcLongitude, double srcLatitude, String srcStreet, String airline, float price, User user) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.srcName = srcName;
        this.destName = destName;
        this.srcLongitude = srcLongitude;
        this.srcLatitude = srcLatitude;
        this.srcStreet = srcStreet;
        this.airline = airline;
        this.price = price;
        this.userId = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public double getSrcLongitude() {
        return srcLongitude;
    }

    public void setSrcLongitude(double srcLongitude) {
        this.srcLongitude = srcLongitude;
    }

    public double getSrcLatitude() {
        return srcLatitude;
    }

    public void setSrcLatitude(double srcLatitude) {
        this.srcLatitude = srcLatitude;
    }

    public String getSrcStreet() {
        return srcStreet;
    }

    public void setSrcStreet(String srcStreet) {
        this.srcStreet = srcStreet;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTicket)) {
            return false;
        }
        UserTicket other = (UserTicket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.airaid.EntityBeans.UserTicket[ id=" + id + " ]";
    }
    
}