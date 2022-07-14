package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
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
        return null;
    }

    @Override
    public Park getParkById(int parkId)
    {
        return null;
    }
}
