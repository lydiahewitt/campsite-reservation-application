package com.techelevator.views;

import com.techelevator.models.dto.Park;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UserInterface
{
    private static Scanner in = new Scanner(System.in);

    public static void setIn(InputStream inStream)
    {
        in = new Scanner(inStream);
    }

    public static String getHomeScreenSelection(){

        System.out.println();
        System.out.println("What do you want to do? ");
        System.out.println("1) List Parks");
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

    public static void displayParkDetail(Park park)
    {
        System.out.println(park.getName());
        System.out.println("--------------------");
        System.out.println("Area: " + park.getArea());
    }
}
