package com.techelevator.models.dao;

import com.techelevator.models.dto.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcReservationDao implements ReservationDao {

    JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Reservation> createReservation(int parkId, int campgroundId, LocalDate fromDate, LocalDate toDate) {

        List<Reservation> currentReservations = new ArrayList<>();
        List<Reservation> availableReservations = new ArrayList<>();

        String sql = "SELECT r.reservation_id, p.park_id, c.campground_id, s.site_id, r.from_date, r.to_date, c.daily_fee " +
                "FROM reservation r " +
                "JOIN site s ON s.site_id = r.site_id " +
                "JOIN campground c ON c.campground_id = s.campground_id " +
                "JOIN park p ON p.park_id = c.park_id " +
                "WHERE p.park_id = ? AND c.campground_id = ? " +
                "  AND ((r.from_date <= ? AND r.to_date >= ?) OR (r.from_date <= ? AND r.to_date >= ?)); ";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId, campgroundId,
                fromDate, fromDate, toDate, toDate);

        while (result.next()){
            currentReservations.add(mapRowToReservation(result));
        }

        String nextSql = "SELECT r.reservation_id, p.park_id, c.campground_id, s.site_id, r.from_date, r.to_date, c.daily_fee " +
                "FROM reservation r " +
                "JOIN site s ON s.site_id = r.site_id " +
                "JOIN campground c ON c.campground_id = s.campground_id " +
                "JOIN park p ON p.park_id = c.park_id " +
                "WHERE site_id NOT IN ?; ";

        SqlRowSet availabilityResults = jdbcTemplate.queryForRowSet(nextSql, currentReservations);

        while (availabilityResults.next()){
            availableReservations.add(mapRowToReservation(availabilityResults));
        }

        return availableReservations;
    }



    private Reservation mapRowToReservation(SqlRowSet rowSet){
        Reservation reservation = new Reservation();

        reservation.setReservationId(rowSet.getInt("reservation_id"));
        reservation.setParkId(rowSet.getInt("park_id"));
        reservation.setCampgroundId(rowSet.getInt("campground_id"));
        reservation.setSideId(rowSet.getInt("site_id"));
        reservation.setFromDate(rowSet.getDate("from_date"));
        reservation.setFromDate(rowSet.getDate("to_date"));

        return reservation;
    }


}
