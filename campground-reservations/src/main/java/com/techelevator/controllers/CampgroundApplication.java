package com.techelevator.controllers;

import com.techelevator.models.dao.*;
import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Park;
import com.techelevator.views.UserInterface;
import com.techelevator.models.dao.ReservationDao;
//import org.graalvm.compiler.lir.LIRInstruction;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void run() {

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
                handleReservation(parkId, campgroundId);


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

    private static void handleReservation(int parkId, int campgroundId){

        String name = UserInterface.getUserName();

        String startString = UserInterface.getStartDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate startDate = LocalDate.parse(startString,formatter);

        String toString = UserInterface.getToDate();
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate toDate = LocalDate.parse(toString,formatter);

       // List<ReservationDao> available = ReservationDao.c
        // LEFT OFF HERE



    }

}
