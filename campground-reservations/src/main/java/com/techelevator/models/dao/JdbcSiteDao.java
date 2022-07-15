package com.techelevator.models.dao;

import com.techelevator.models.dto.Reservation;
import com.techelevator.models.dto.Site;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcSiteDao implements SiteDao {
    JdbcTemplate jdbcTemplate;

    public JdbcSiteDao(DataSource dataSource)
    {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Site getSiteByID(int siteId){

        Site site = null;
        String sql = "SELECT * FROM site WHERE site_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, siteId);
        if(results.next()){
            site = mapRowToSite(results);
        }
        return site;
    }

    public List<Site> getListOfSitesAvailable(int parkId, int campgroundId, LocalDate fromDate, LocalDate toDate,
                                               int arrivalMonth, int departureMonth) {

        List<Site> availableSites = new ArrayList<>();

        String sql = "SELECT s.*\n" +
                "FROM site s\n" +
                "INNER JOIN campground c\n" +
                "ON s.campground_id = c.campground_id\n" +
                "WHERE s.campground_id = ?\n" +
                "  AND cast(c.open_from_mm as int) <= ?\n" +
                "  AND cast(c.open_to_mm as int) > ?\n" +
                "      AND site_id NOT IN (\n" +
                "    SELECT s.site_id\n" +
                "    FROM reservation r\n" +
                "    JOIN site s ON s.site_id = r.site_id\n" +
                "    JOIN campground c ON c.campground_id = s.campground_id\n" +
                "    JOIN park p ON p.park_id = c.park_id\n" +
                "    WHERE p.park_id = ? AND c.campground_id = ?\n" +
                "    AND ((r.from_date <= ? AND r.to_date >= ?) OR (r.from_date <= ? AND r.to_date >= ?))\n" +
                ")\n" +
                "LIMIT 5;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, campgroundId, arrivalMonth, departureMonth,
                parkId, campgroundId, fromDate, fromDate, toDate, toDate);

        while (result.next()) {
            availableSites.add(mapRowToSite(result));
        }
        return availableSites;
    }

    public List<Site> getListOfSitesAvailable2(int parkId, int campgroundId, LocalDate fromDate, LocalDate toDate,
                                              int arrivalMonth, int departureMonth) {

        List<Site> availableSites = new ArrayList<>();

        String sql = "SELECT s.*\n" +
                "FROM site s\n" +
                "INNER JOIN campground c\n" +
                "ON s.campground_id = c.campground_id\n" +
                "WHERE s.campground_id = ?\n" +
                "  AND cast(c.open_from_mm as int) <= ?\n" +
                "  AND cast(c.open_to_mm as int) > ?\n" +
                "      AND site_id NOT IN (\n" +
                "    SELECT s.site_id\n" +
                "    FROM reservation r\n" +
                "    JOIN site s ON s.site_id = r.site_id\n" +
                "    JOIN campground c ON c.campground_id = s.campground_id\n" +
                "    JOIN park p ON p.park_id = c.park_id\n" +
                "    WHERE p.park_id = ? AND c.campground_id = ?\n" +
                "    AND ((r.from_date <= ? AND r.to_date >= ?) OR (r.from_date <= ? AND r.to_date >= ?))\n" +
                ")\n" +
                "LIMIT 5;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, campgroundId, arrivalMonth, departureMonth,
                parkId, campgroundId, fromDate, fromDate, toDate, toDate);

        while (result.next()) {
            availableSites.add(mapRowToSite(result));
        }
        return availableSites;
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
