package com.techelevator.models.dao;

import com.techelevator.models.dto.Site;

import java.time.LocalDate;
import java.util.List;

public interface SiteDao {

    Site getSiteByID(int siteId);

    List<Site> getListOfSitesAvailable(int parkId, int campgroundId, LocalDate fromDate, LocalDate toDate, int arrivalMonth, int departureMonth);
}
