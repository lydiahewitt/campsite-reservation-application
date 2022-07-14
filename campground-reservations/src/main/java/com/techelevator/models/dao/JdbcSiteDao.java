package com.techelevator.models.dao;

import com.techelevator.models.dto.Site;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcSiteDao implements SiteDao {
    JdbcTemplate jdbcTemplate;

    public JdbcSiteDao(DataSource dataSource)
    {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Site getSitebyID(int siteId){

        Site site = null;

        String sql = "SELECT * FROM site WHERE site_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, siteId);

        if(results.next()){
            site = mapRowToSite(results);
        }
        return site;
    }

    public static Site mapRowToSite(SqlRowSet rowSet){
        Site site = new Site();

        site.setSiteId(rowSet.getInt("site_id"));
        site.setCampgroundId(rowSet.getInt("campground_id"));
        site.setSiteNumber(rowSet.getInt("site_number"));
        site.setMaxOccupancy(rowSet.getInt("max_occupancy"));
        site.setAccessibility(rowSet.getBoolean("accessible"));
        site.setMaxRvLength(rowSet.getInt("max_rv_length"));
        site.setUtilities(rowSet.getBoolean("utilities"));

        return site;

    }
}
