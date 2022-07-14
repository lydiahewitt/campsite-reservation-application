package com.techelevator.models.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcReservationDao implements ReservationDao {

    JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createReservation(String parkId) {

    }
}
