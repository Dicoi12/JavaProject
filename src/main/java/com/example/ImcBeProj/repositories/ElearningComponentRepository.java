package com.example.ImcBeProj.repositories;

import com.example.ImcBeProj.models.ElearningComponent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class ElearningComponentRepository extends GenericRepository<ElearningComponent, String> {
    private static final RowMapper<ElearningComponent> rowMapper = (rs, rowNum) -> {
        ElearningComponent ec = new ElearningComponent(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("imageUrl"),
            rs.getString("description"),
            rs.getFloat("duration"),
            rs.getString("type"),
            rs.getShort("category_id"),
            rs.getObject("startDate", Timestamp.class) != null ? rs.getObject("startDate", Timestamp.class).toInstant().atOffset(java.time.ZoneOffset.UTC) : null,
            rs.getObject("endDate", Timestamp.class) != null ? rs.getObject("endDate", Timestamp.class).toInstant().atOffset(java.time.ZoneOffset.UTC) : null
        );
        return ec;
    };

    public ElearningComponentRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "elearings", rowMapper);
    }

    @Override
    public ElearningComponent save(ElearningComponent ec) {
        String sql = "INSERT INTO elearings (id, name, image_url, description, duration, type, category_id, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ec.getId(), ec.getName(), ec.getImageUrl(), ec.getDescription(), ec.getDuration(), ec.getType(), ec.getCategoryId(), ec.getStartDate(), ec.getEndDate());
        return ec;
    }

    @Override
    public ElearningComponent update(ElearningComponent ec) {
        String sql = "UPDATE elearings SET name=?, image_url=?, description=?, duration=?, type=?, category_id=?, startDate=?, endDate=? WHERE id=?";
        jdbcTemplate.update(sql, ec.getName(), ec.getImageUrl(), ec.getDescription(), ec.getDuration(), ec.getType(), ec.getCategoryId(), ec.getStartDate(), ec.getEndDate(), ec.getId());
        return ec;
    }
} 