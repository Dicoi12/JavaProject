package com.example.ImcBeProj.repositories;

import com.example.ImcBeProj.models.ElearningUser;
import com.example.ImcBeProj.models.dtos.ElearningComponentDto;
import com.example.ImcBeProj.models.dtos.ElearningDates;
import com.example.ImcBeProj.models.dtos.ElearningForUserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ElearningUserRepository extends GenericRepository<ElearningUser, Long> {
    private static final RowMapper<ElearningUser> rowMapper = (rs, rowNum) -> {
        return new ElearningUser(
            rs.getLong("id"),
            rs.getString("elearning_id"),
            rs.getLong("user_id"),
            rs.getObject("startDate", Timestamp.class) != null ? rs.getObject("startDate", Timestamp.class).toInstant().atOffset(java.time.ZoneOffset.UTC) : null,
            rs.getObject("endDate", Timestamp.class) != null ? rs.getObject("endDate", Timestamp.class).toInstant().atOffset(java.time.ZoneOffset.UTC) : null,
            rs.getString("status")
        );
    };

    public ElearningUserRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "elearning_users", rowMapper);
    }

    @Override
    public ElearningUser save(ElearningUser eu) {
        String sql = "INSERT INTO elearning_users (elearning_id, user_id, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, eu.getElearningId(), eu.getUserId(), eu.getStartDate(), eu.getEndDate(), eu.getStatus());
        return eu;
    }

    @Override
    public ElearningUser update(ElearningUser eu) {
        String sql = "UPDATE elearning_users SET elearning_id=?, user_id=?, startDate=?, endDate=?, status=? WHERE id=?";
        jdbcTemplate.update(sql, eu.getElearningId(), eu.getUserId(), eu.getStartDate(), eu.getEndDate(), eu.getStatus(), eu.getId());
        return eu;
    }

    public List<ElearningUser> findByUserId(Long userId) {
        String sql = "SELECT * FROM elearning_users WHERE user_id = ?";
        return jdbcTemplate.query(sql, rowMapper, userId);
    }

    public List<ElearningForUserDto> getUserElearnings(long userId, int pageNumber, int pagesize) {
        String baseSql = "SELECT " +
                "  e.id AS id, " +
                "  e.name AS name, " +
                "  e.type AS type, " +
                "  eu.\"startDate\" AS startDate, " +
                "  eu.\"endDate\" AS endDate, " +
                "  eu.status AS status, " +
                "  e.\"imageUrl\" AS imageUrl " +
                "FROM elearning_users eu " +
                "JOIN elearings e ON eu.elearning_id = e.id " +
                "WHERE eu.user_id = ?";

        List<Object> params = new ArrayList<>();
        params.add(userId);
        if (pagesize > 0) {
            int offset = (pageNumber - 1) * pagesize;
            if (offset < 0) offset = 0; 
            baseSql = baseSql + " LIMIT ? OFFSET ?";
            params.add(pagesize);
            params.add(offset);
        }

        return jdbcTemplate.query(baseSql, params.toArray(), (rs, rowNum) -> {
            ElearningDates assignedDates = new ElearningDates(
                    rs.getTimestamp("startDate") != null ? rs.getTimestamp("startDate").toInstant().atOffset(java.time.ZoneOffset.UTC) : null,
                    rs.getTimestamp("endDate") != null ? rs.getTimestamp("endDate").toInstant().atOffset(java.time.ZoneOffset.UTC) : null
            );
            return new ElearningForUserDto(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    assignedDates,
                    rs.getString("status"),
                    rs.getString("imageUrl")
            );
        });
    }

    public Optional<ElearningComponentDto> getUserSpecificElearning(long userId, String componentId) {
        String sql = "SELECT " +
                "  e.id AS id, " +
                "  e.name AS name, " +
                "  e.description AS description, " +
                "  e.duration AS duration, " +
                "  e.type AS type, " +
                "  e.\"startDate\" AS startDate, " +
                "  e.\"endDate\" AS endDate, " +
                "  eu.status AS status, " +
                "  e.\"imageUrl\" AS imageUrl, " +
                "  ec.name AS category, "+
                "  (\n" +
                "    SELECT array_agg(m.name)\n" +
                "    FROM elearning_metatags em\n" +
                "    JOIN metatags m ON em.metatag_id = m.id\n" +
                "    WHERE em.elearning_id = e.id\n" +
                "  ) AS metatags "+
                "FROM elearning_users eu " +
                "JOIN elearings e ON eu.elearning_id = e.id " +
                "JOIN elearning_categories ec ON e.category_id = ec.id " +
                "WHERE eu.user_id = ? AND e.id = ?";


        List<ElearningComponentDto> list=jdbcTemplate.query(sql, (rs, rowNum) -> {
            ElearningDates availableDates = new ElearningDates(
                rs.getTimestamp("startDate") != null ? rs.getTimestamp("startDate").toInstant().atOffset(java.time.ZoneOffset.UTC) : null,
                rs.getTimestamp("endDate") != null ? rs.getTimestamp("endDate").toInstant().atOffset(java.time.ZoneOffset.UTC) : null
            );
            return new ElearningComponentDto(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("type"),
                availableDates,
                rs.getString("imageUrl"),
                rs.getString("status"),
        rs.getFloat("duration")+" hours",
                rs.getString("category"),
                rs.getArray("metatags") != null ? (String[]) rs.getArray("metatags").getArray() : new String[0]
            );
        }, userId,componentId);
        return list.stream().findFirst();
    }
} 