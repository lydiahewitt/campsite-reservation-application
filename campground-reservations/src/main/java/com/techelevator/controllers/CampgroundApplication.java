package com.techelevator.controllers;

import com.techelevator.models.dao.*;
import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Reservation;
import com.techelevator.models.dto.Site;
import com.techelevator.views.UserInterface;
import com.techelevator.models.dao.ReservationDao;

import javax.sql.DataSource;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
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

            //start branching here

            //1) See campsites?
            //2) View upcoming reservation?
            //3) View availability across whole park?


            if(userChoice.equalsIgnoreCase("Q")) {
                break;
            }

            int parkId = Integer.parseInt(userChoice);
            displayParkDetails(parkId);

            //call second homescreen
            //potential branch

            userChoice = UserInterface.getSecondScreenSelection();

            if(userChoice.equalsIgnoreCase("reserve")){
                //normal

                    List<Campground> campgrounds = campgroundDao.getCampgroundsByParkId(parkId);
                    String chosenCampground = UserInterface.displayAllCampgrounds(campgrounds);

                    int campgroundId = Integer.parseInt(chosenCampground);
                    displayCampDetails(campgroundId);

                    //start reservation setup
                    findSitesAvailable(parkId, campgroundId);

                return;


            } else if (userChoice.equalsIgnoreCase("upcoming")){
                //vew upcoming

                List<Reservation> reservationList = reservationDao.getUpcomingReservations(parkId);
                UserInterface.displayUpcomingReservations(reservationList);

                //set up booking
                String choice = UserInterface.makeReservation();

                if (choice.equalsIgnoreCase("Y")) {
                    List<Campground> campgrounds = campgroundDao.getCampgroundsByParkId(parkId);
                    String chosenCampground = UserInterface.displayAllCampgrounds(campgrounds);

                    int campgroundId = Integer.parseInt(chosenCampground);
                    displayCampDetails(campgroundId);

                    //start reservation setup
                    findSitesAvailable(parkId, campgroundId);


                } return;

            } else if (userChoice.equalsIgnoreCase("wholepark")){

                List<Campground> campgrounds = campgroundDao.getCampgroundsByParkId(parkId);
                LocalDate arrivalDate = UserInterface.getArrivalDate();
                LocalDate departureDate = UserInterface.getDepartureDate();
                int arrivalMonth = arrivalDate.getMonthValue();
                int departureMonth = departureDate.getMonthValue();
                List<Site> allSites = new ArrayList<>();
                for (Campground campground : campgrounds) {
                    int campgroundId = campground.getCampgroundId();
                    List<Site> sitesAvailableForGivenCampground = siteDao.getListOfSitesAvailable(parkId,
                            campgroundId, arrivalDate, departureDate, arrivalMonth, departureMonth);
                    allSites.addAll(sitesAvailableForGivenCampground);
                    Period period = Period.between(arrivalDate, departureDate);
                    int lengthOfStay = Math.abs(period.getDays());
                    UserInterface.displayAvailableSites(sitesAvailableForGivenCampground, campground, lengthOfStay);
                }
                completeReservation(arrivalDate, departureDate, allSites);

            }else if (userChoice.equalsIgnoreCase("exit")){
                break;
            } else {
                break;
            }


//            String choice = UserInterface.makeReservation();
//
//            if (choice.equalsIgnoreCase("Y")) {
//                String upcomingChoice = UserInterface.viewUpcomingReservations();
//                //option to view future reservation
//                if (upcomingChoice.equalsIgnoreCase("Y")) {
//                    List<Reservation> reservationList = reservationDao.getUpcomingReservations(parkId);
//                    UserInterface.displayUpcomingReservations(reservationList);
//                } break;
//            }
//
//            if (choice.equalsIgnoreCase("Y")) {
//                List<Campground> campgrounds = campgroundDao.getCampgroundsByParkId(parkId);
//                String chosenCampground = UserInterface.displayAllCampgrounds(campgrounds);
//
//                int campgroundId = Integer.parseInt(chosenCampground);
//                displayCampDetails(campgroundId);
//
//                //start reservation setup
//                findSitesAvailable(parkId, campgroundId);
//
//
//            } return;

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

    private void findSitesAvailable(int parkId, int campgroundId) {
        //get date inputs and convert to Date type
        LocalDate arrivalDate = UserInterface.getArrivalDate();
        LocalDate departureDate = UserInterface.getDepartureDate();
        int arrivalMonth = arrivalDate.getMonthValue();
        int departureMonth = departureDate.getMonthValue();

        if (departureDate.compareTo(arrivalDate) < 0) {
            System.out.println("Departure date cannot be before your arrival date.");
        }

        Campground campground = campgroundDao.getCampgroundById(campgroundId);
        List<Site> availableSitesList = siteDao.getListOfSitesAvailable(parkId,
                campgroundId, arrivalDate, departureDate, arrivalMonth, departureMonth);

        if (availableSitesList.size() == 0) {
            String userChoice = UserInterface.tryAgain();
            if (userChoice.equalsIgnoreCase("yes")) {
                findSitesAvailable(parkId, campgroundId);
            } else {
                System.out.println("Hope to see you again soon!");
                return;
            }
        }

        Period period = Period.between(arrivalDate, departureDate);
        int lengthOfStay = Math.abs(period.getDays());

        UserInterface.displayAvailableSites(availableSitesList, campground, lengthOfStay);

        completeReservation(arrivalDate, departureDate, availableSitesList);


    }



    private void completeReservation(LocalDate arrivalDate, LocalDate departureDate, List<Site> sites) {

        int siteId = UserInterface.getChosenSite(sites);
        String customerName = UserInterface.getUserName();
        reservationDao.addReservation(siteId, customerName, arrivalDate, departureDate);
        LocalDate today = LocalDate.now();
        String todayString = today.toString();
        LocalTime time = LocalTime.now();
        String timeString = time.toString();

        String confirmationString = todayString + customerName.charAt(0) + customerName.charAt(customerName.length()-1)
                + timeString;
        System.out.println();
        System.out.println("Here is your confirmation code: " + confirmationString);
        System.out.println();
        UserInterface.waitForEnter();
    }



}
