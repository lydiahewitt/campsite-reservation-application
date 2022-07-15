package com.techelevator.views;

import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface
{
    private static Scanner in = new Scanner(System.in);

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public static void setIn(InputStream inStream)
    {
        in = new Scanner(inStream);
    }

    public static String getHomeScreenSelection(){

        System.out.println();
        System.out.println("What do you want to do? ");
        System.out.println("1) View Parks");
        System.out.println("2) Make Reservation");
        System.out.println("3) Exit");
        System.out.println();
        System.out.print("Please make a selection: ");
        String choice = in.nextLine().trim().toLowerCase();

        switch (choice) {
            case "1":
                return "list";
            case "2":
                return "reserve";
            case "3":
                return "exit";
            default:
                return "invalid";
        }
    }

    public static String displayAllParks(List<Park> parks){
        int counter = 1;
        for (Park park : parks){
            System.out.println(counter + ") " + park.getName());
            counter++;
        }
        System.out.println("E) Exit ");
        System.out.println();
        System.out.println("Please select a park: ");
        return in.nextLine().trim();
    }


    public static void displayParkDetail(Park park)
    {
        System.out.println(park.getName());
        System.out.println("--------------------");
        System.out.println("Location: " + park.getLocation());
        System.out.println("Date Established: " + park.getDate());
        System.out.println("Area: " + park.getArea());
        System.out.println("Visitors: " + park.getVisitors());
        System.out.println("Description: " + park.getDescription());
        System.out.println();

    }

    public static String tryAgain() {
        System.out.println("Would you like to try again? (Y/N)");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine().trim();

        if (userInput.equalsIgnoreCase("Y")) {
            return "yes";
        } else {
            return "no";
        }
    }


    public static String makeReservation() {
        System.out.println();
        System.out.println("Would you like to see campgrounds and make a reservation? (Y/N) ");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine().trim();

        if (userInput.equalsIgnoreCase("Y")) {
            return "Y";
        } else {
            return "N";
        }
    }


    public static String displayAllCampgrounds(List<Campground> campgrounds) {
        System.out.println();
        System.out.println("Campgrounds Available at This Park: ");
        System.out.println("------------------------------------");
        int counter = 1;
        for (Campground campground : campgrounds){
            System.out.println(counter + ") " + campground.getName());
            counter++;
        }
        System.out.println("E) Exit ");
        System.out.println();
        System.out.println("Please select a campground: ");
        return in.nextLine().trim();
    }

    public static void displayCampgroundDetails(Campground campground){

        System.out.println();
        System.out.println("Name: " + campground.getName());
        System.out.println("------------------------------------");
        System.out.println("Open Month: " + monthConverter(campground.getOpenFromDate()));
        System.out.println("Closing Month: " + monthConverter(campground.getOpenToDate()));
        System.out.println("Daily Fee: " + campground.getDailyFee());
        System.out.println();
    }

    public static String monthConverter(String num){
        String month = "";

        if (num.equals("01")){
            return "January";
        } else if (num.equals("02")){
            return "February";
        }else if (num.equals("03")){
            return "March";
        } else if (num.equals("04")){
            return "April";
        }else if (num.equals("05")){
            return "May";
        }else if (num.equals("06")){
            return "June";
        }else if (num.equals("07")){
            return "July";
        }else if (num.equals("08")){
            return "August";
        }else if (num.equals("09")){
            return "September";
        }else if (num.equals("10")){
            return "October";
        }else if (num.equals("11")){
            return "November";
        }else if (num.equals("12")){
            return "December";
        }
        return month;
    }

    public static String getUserName(){
        System.out.println();
        System.out.println("Please enter name: ");

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();

        return name;
    }

    public static LocalDate getArrivalDate(){
        System.out.println();
        System.out.println("Please enter arrival date (mm/dd/yyyy): ");
        Scanner in = new Scanner(System.in);
        String arrivalString = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate arrivalDate = LocalDate.parse(arrivalString, formatter);
        return arrivalDate;
    }

    public static LocalDate getDepartureDate(){
        System.out.println();
        System.out.println("Please enter departure date (mm/dd/yyyy): ");
        Scanner in = new Scanner(System.in);
        String departureString = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate departureDate = LocalDate.parse(departureString, formatter);
        return departureDate;
    }

    public static void displayAvailableSites(List<Reservation> reservations){

        for (Reservation reservation: reservations){
            System.out.println();
            System.out.println("List of available sites");
            System.out.println("------------------------------------");
            System.out.println();
        }
    }
}
