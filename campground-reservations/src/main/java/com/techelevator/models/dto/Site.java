package com.techelevator.models.dto;

public class Site {

    int siteId;
    int campgroundId;
    int siteNumber;
    int maxOccupancy;
    boolean accessibility;
    int maxRvLength;
    boolean utilities;

    public Site() {
    }

    public Site(int siteId, int campgroundId, int siteNumber, int maxOccupancy, boolean accessibility, int maxRvLength, boolean utilities) {
        this.siteId = siteId;
        this.campgroundId = campgroundId;
        this.siteNumber = siteNumber;
        this.maxOccupancy = maxOccupancy;
        this.accessibility = accessibility;
        this.maxRvLength = maxRvLength;
        this.utilities = utilities;
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

    public int getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(int siteNumber) {
        this.siteNumber = siteNumber;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public boolean isAccessibility() {
        return accessibility;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    public int getMaxRvLength() {
        return maxRvLength;
    }

    public void setMaxRvLength(int maxRvLength) {
        this.maxRvLength = maxRvLength;
    }

    public boolean isUtilities() {
        return utilities;
    }

    public void setUtilities(boolean utilities) {
        this.utilities = utilities;
    }
}
