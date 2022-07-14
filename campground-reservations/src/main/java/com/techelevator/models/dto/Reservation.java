package com.techelevator.models.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Reservation {
    private int reservationId;
    private int parkId;
    private int campgroundId;
    private int sideId;
    private Date fromDate;
    private Date toDate;
    private BigDecimal dailyFee;

    public Reservation() {

    }

    public Reservation(int reservationId, int parkId, int campgroundId, int sideId, Date fromDate, Date toDate, BigDecimal dailyFee) {
        this.reservationId = reservationId;
        this.parkId = parkId;
        this.campgroundId = campgroundId;
        this.sideId = sideId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.dailyFee = dailyFee;
    }

    public BigDecimal getDailyFee() {
        return dailyFee;
    }

    public void setDailyFee(BigDecimal dailyFee) {
        this.dailyFee = dailyFee;
    }

    public int getSideId() {
        return sideId;
    }

    public void setSideId(int sideId) {
        this.sideId = sideId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
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
