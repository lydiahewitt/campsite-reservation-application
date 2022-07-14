package com.techelevator.models.dto;

import java.math.BigDecimal;

public class Campground {

    private int campgroundId;
    private int parkId;
    String name;
    String openFromDate;
    String openToDate;
    BigDecimal dailyFee;

    public Campground (){
    }

    public Campground(int campgroundId, int parkId, String name, String openFromDate, String openToDate, BigDecimal dailyFee) {
        this.campgroundId = campgroundId;
        this.parkId = parkId;
        this.name = name;
        this.openFromDate = openFromDate;
        this.openToDate = openToDate;
        this.dailyFee = dailyFee;
    }


    public int getCampgroundId() {
        return campgroundId;
    }

    public void setCampgroundId(int campgroundId) {
        this.campgroundId = campgroundId;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenFromDate() {
        return openFromDate;
    }

    public void setOpenFromDate(String openFromDate) {
        this.openFromDate = openFromDate;
    }

    public String getOpenToDate() {
        return openToDate;
    }

    public void setOpenToDate(String openToDate) {
        this.openToDate = openToDate;
    }

    public BigDecimal getDailyFee() {
        return dailyFee;
    }

    public void setDailyFee(BigDecimal dailyFee) {
        this.dailyFee = dailyFee;
    }
}
