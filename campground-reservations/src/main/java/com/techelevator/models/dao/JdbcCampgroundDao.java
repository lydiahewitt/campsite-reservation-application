package com.techelevator.models.dao;

import com.techelevator.models.dto.Campground;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCampgroundDao implements CampgroundDao {

    JdbcTemplate jdbcTemplate;

    public JdbcCampgroundDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Campground> getCampgroundsByParkId(int parkId) {

        List<Campground> campgrounds = new ArrayList<>();
        String sql = "SELECT c.campground_id, " +
                "c.park_id ," +
                "c.name ," +
                "c.open_from_mm, " +
                "c.open_to_mm, " +
                "c.daily_fee " +
                "FROM park p\n" +
                "JOIN campground c on p.park_id = c.park_id\n" +
                "WHERE p.park_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
        while (results.next()) {
            campgrounds.add(mapRowToCampground(results));

        }
        return campgrounds;
    }

    public Campground getCampgroundById(int campgroundId){

        Campground campground = null;

        String sql = "SELECT c.campground_id, " +
                "c.park_id ," +
                "c.name ," +
                "c.open_from_mm, " +
                "c.open_to_mm, " +
                "c.daily_fee " +
                "FROM park p\n" +
                "JOIN campground c on p.park_id = c.park_id\n" +
                "WHERE c.campground_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, campgroundId);

        if(results.next()){
            campground = mapRowToCampground(results);
        }
        return campground;
    }

    public Campground mapRowToCampground(SqlRowSet rowset) {
        Campground campground = new Campground();
        campground.setCampgroundId(rowset.getInt("campground_id"));
        campground.setParkId(rowset.getInt("park_id"));
        campground.setName(rowset.getString("name"));
        campground.setOpenFromDate(rowset.getString("open_from_mm"));
        campground.setOpenToDate(rowset.getString("open_to_mm"));
        campground.setDailyFee(rowset.getBigDecimal("daily_fee"));
        return campground;
    }
}
