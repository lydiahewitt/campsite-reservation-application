package com.techelevator.controllers;

import com.techelevator.models.dao.*;
import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Reservation;
import com.techelevator.views.UserInterface;
import com.techelevator.models.dao.ReservationDao;

import javax.sql.DataSource;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CampgroundApplication
{

    private final ParkDao parkDao;
    private final SiteDao siteDao;
    private final CampgroundDao campgroundDao;
    private final ReservationDao reservationDao;


    public CampgroundApplication(DataSource dataSource) {

        parkDao = new JdbcParkDao(dataSource);
        campgroundDao = new JdbcCampgroundDao(dataSource);
        siteDao = new JdbcSiteDao(dataSource);
        reservationDao = new JdbcReservationDao(dataSource);

    }

    public void run() throws ParseException {

        List<Park> parks = parkDao.getAllParks();

        while (true) {

            String userChoice = UserInterface.displayAllParks(parks);
            if(userChoice.equalsIgnoreCase("Q")) {
                break;
            }

            int parkId = Integer.parseInt(userChoice);
            displayParkDetails(parkId);
            String choice = UserInterface.makeReservation();
            if (choice.equalsIgnoreCase("Y")) {
                List<Campground> campgrounds = campgroundDao.getCampgroundsByParkId(parkId);
                String chosenCampground = UserInterface.displayAllCampgrounds(campgrounds);

                int campgroundId = Integer.parseInt(chosenCampground);
                displayCampDetails(campgroundId);

                //start reservation setup
                findReservationsAvailable(parkId, campgroundId);
                completeReservation();


            } return;

        }

    }

    private Park displayParkDetails(int parkId) {

        Park chosenPark = parkDao.getParkById(parkId);
        UserInterface.displayParkDetail(chosenPark);
        return chosenPark;
    }

    private Campground displayCampDetails(int campId){
        Campground campground = campgroundDao.getCampgroundById(campId);
        UserInterface.displayCampgroundDetails(campground);
        return campground;
    }

    private List<Reservation> findReservationsAvailable(int parkId, int campgroundId) {

        //get date inputs and convert to Date type
        LocalDate arrivalDate = UserInterface.getArrivalDate();
        LocalDate departureDate = UserInterface.getDepartureDate();

        if (departureDate.compareTo(arrivalDate) < 0) {
            System.out.println("Departure date cannot be before your arrival date.");
        }

        List<Reservation> availableReservationsList = reservationDao.createReservation(parkId,
                campgroundId, arrivalDate, departureDate);
        // LEFT OFF HERE

        return availableReservationsList;
    }

    private void completeReservation() {

        String customerName = UserInterface.getUserName();

    }



}
