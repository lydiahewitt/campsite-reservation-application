package com.techelevator.models.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Reservation {
    private int campgroundId;
    private int siteId;
    private Date fromDate;
    private Date toDate;

    public Reservation() {

    }

    public Reservation(int campgroundId, int siteId, Date fromDate, Date toDate) {
        this.campgroundId = campgroundId;
        this.siteId = siteId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getCampgroundId() {
        return campgroundId;
    }

    public void setCampgroundId(int campgroundId) {
        this.campgroundId = campgroundId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
