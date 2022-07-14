package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import com.techelevator.views.UserInterface;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDao implements ParkDao
{
    JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Park> getAllParks()
    {
            List<Park> parks = new ArrayList<>();
            String sql = "SELECT p.park_id, " +
                    "p.name, " +
                    "p.location, " +
                    "p.establish_date, " +
                    "p.area, " +
                    "p.visitors, " +
                    "p.description " +
                    "FROM park p ;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                parks.add(mapRowToPark(results));
            }
        return parks;
    }


    @Override
    public Park getParkById(int parkId)
    {
        Park newPark = null;

        String sql = "SELECT p.park_id, " +
                "p.name, " +
                "p.location, " +
                "p.establish_date, " +
                "p.area, " +
                "p.visitors, " +
                "p.description " +
                "FROM park p " +
                "WHERE p.park_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
        if (results.next()) {
            newPark = mapRowToPark(results);
    } return newPark;
    }


    private Park mapRowToPark(SqlRowSet rowSet) {
        Park park = new Park();
        park.setParkId(rowSet.getInt("park_id"));
        park.setName(rowSet.getString("name"));
        park.setLocation(rowSet.getString("location"));
        park.setDate(rowSet.getDate("establish_date").toLocalDate());
        park.setArea(rowSet.getInt("area"));
        park.setVisitors(rowSet.getInt("visitors"));
        park.setDescription(rowSet.getString("description"));
        return park;
    }
}
