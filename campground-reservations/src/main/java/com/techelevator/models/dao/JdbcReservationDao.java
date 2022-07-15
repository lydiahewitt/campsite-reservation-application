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


    public Reservation mapRowToReservation(SqlRowSet rowSet) {
        Reservation reservation = new Reservation();
        reservation.setCampgroundId(rowSet.getInt("campground_id"));
        reservation.setSiteId(rowSet.getInt("site_id"));
        reservation.setFromDate(rowSet.getDate("from_date"));
        reservation.setToDate(rowSet.getDate("to_date"));

        return reservation;
    }

    public void addReservation(int siteId, String name, LocalDate arrivalDate, LocalDate departureDate) {
        LocalDate today = LocalDate.now();
        String sql = "INSERT INTO reservation (site_id, name, from_date, to_date, create_date)\n" +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, siteId, name, arrivalDate, departureDate, today);
    }

    public List<Reservation> getUpcomingReservations(int parkId) {

        List<Reservation> reservationsList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate thirtyDays = today.plusDays(30);
        String sql = "SELECT r.site_id, c.campground_id, r.from_date, r.to_date\n" +
                "\n" +
                "FROM reservation r\n" +
                "         JOIN site s\n" +
                "             on s.site_id = r.site_id\n" +
                "         JOIN campground c\n" +
                "             on c.campground_id = s.campground_id\n" +
                "WHERE c.park_id = ? AND r.from_date >= ?\n" +
                "AND r.to_date < ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId, today, thirtyDays);
        while (results.next()) {
            reservationsList.add(mapRowToReservation(results));
        }
        return reservationsList;
    }







}
