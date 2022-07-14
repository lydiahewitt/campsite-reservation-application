package com.techelevator.models.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcSiteDao implements SiteDao {

    public JdbcSiteDao(DataSource dataSource)
    {
        JdbcTemplate jdbcTemplate;

        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
