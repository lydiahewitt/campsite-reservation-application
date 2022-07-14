package com.techelevator.views;

import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Campground;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import com.techelevator.models.dao.ParkDao;
import com.techelevator.models.dao.SiteDao;
import com.techelevator.models.dao.ReservationDao;
import com.techelevator.models.dao.CampgroundDao;
import com.techelevator.models.dao.JdbcParkDao;
import com.techelevator.models.dao.JdbcSiteDao;
import com.techelevator.models.dao.JdbcReservationDao;
import com.techelevator.models.dao.JdbcCampgroundDao;

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
        System.out.println("Would you like to make a reservation? (Y/N) ");
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

}
