package com.techelevator.models.dao;

import com.techelevator.models.dto.Reservation;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao {

    void addReservation(int siteId, String name, LocalDate arrivalDate, LocalDate departureDate);

    Reservation mapRowToReservation(SqlRowSet rowset);

    List<Reservation> getUpcomingReservations(int parkId);


}
