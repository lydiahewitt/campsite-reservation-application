package com.techelevator;

import javax.sql.DataSource;

import com.techelevator.controllers.CampgroundApplication;
import com.techelevator.models.dao.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.awt.*;
import java.util.Scanner;

public class CampgroundCLI {


	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundApplication application = new CampgroundApplication(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource dataSource) {

	}


}
