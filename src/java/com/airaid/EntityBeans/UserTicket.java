/*
 * Created by Viet Doan on 2018.11.27  * 
 * Copyright © 2018 Viet Doan. All rights reserved. * 
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
import javax.persistence.Lob;
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
 * @author VDoan
 */
@Entity
@Table(name = "UserTicket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTicket.findAll", query = "SELECT u FROM UserTicket u")
    , @NamedQuery(name = "UserTicket.findById", query = "SELECT u FROM UserTicket u WHERE u.id = :id")
    , @NamedQuery(name = "UserTicket.findByFlightDatetime", query = "SELECT u FROM UserTicket u WHERE u.flightDatetime = :flightDatetime")
    , @NamedQuery(name = "UserTicket.findByFlightId", query = "SELECT u FROM UserTicket u WHERE u.flightId = :flightId")
    , @NamedQuery(name = "UserTicket.findBySrcName", query = "SELECT u FROM UserTicket u WHERE u.srcName = :srcName")
    , @NamedQuery(name = "UserTicket.findBySrcLongitude", query = "SELECT u FROM UserTicket u WHERE u.srcLongitude = :srcLongitude")
    , @NamedQuery(name = "UserTicket.findBySrcLatitude", query = "SELECT u FROM UserTicket u WHERE u.srcLatitude = :srcLatitude")
    , @NamedQuery(name = "UserTicket.findByDestName", query = "SELECT u FROM UserTicket u WHERE u.destName = :destName")
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
    @Column(name = "flight_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date flightDatetime;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "expected_travel_time")
    private String expectedTravelTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "flight_id")
    private String flightId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "src_name")
    private String srcName;
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
    @Column(name = "dest_name")
    private String destName;
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

    public UserTicket(Integer id, Date flightDatetime, String expectedTravelTime, String flightId, String srcName, double srcLongitude, double srcLatitude, String destName, float price) {
        this.id = id;
        this.flightDatetime = flightDatetime;
        this.expectedTravelTime = expectedTravelTime;
        this.flightId = flightId;
        this.srcName = srcName;
        this.srcLongitude = srcLongitude;
        this.srcLatitude = srcLatitude;
        this.destName = destName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFlightDatetime() {
        return flightDatetime;
    }

    public void setFlightDatetime(Date flightDatetime) {
        this.flightDatetime = flightDatetime;
    }

    public String getExpectedTravelTime() {
        return expectedTravelTime;
    }

    public void setExpectedTravelTime(String expectedTravelTime) {
        this.expectedTravelTime = expectedTravelTime;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
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

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
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
