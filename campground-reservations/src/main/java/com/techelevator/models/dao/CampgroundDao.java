package com.techelevator.models.dao;

import com.techelevator.models.dto.Campground;

import java.util.List;

public interface CampgroundDao {

    public List<Campground> getCampgroundsByParkId(int parkId);
    public Campground getCampgroundById(int campgroundId);
}
