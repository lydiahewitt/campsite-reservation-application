package com.techelevator.models.dao;

import com.techelevator.models.dto.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao {

    public List<Reservation> createReservation(int parkId, int campgroundId, LocalDate fromDate, LocalDate toDate);

}
