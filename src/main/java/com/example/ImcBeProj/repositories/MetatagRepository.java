package com.example.ImcBeProj.repositories;

import com.example.ImcBeProj.models.Metatag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MetatagRepository extends GenericRepository<Metatag, Integer> {
    private static final RowMapper<Metatag> rowMapper = (rs, rowNum) -> {
        return new Metatag(
            rs.getInt("id"),
            rs.getString("name")
        );
    };

    public MetatagRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "metatags", rowMapper);
    }

    @Override
    public Metatag save(Metatag metatag) {
        String sql = "INSERT INTO metatags (name) VALUES (?)";
        jdbcTemplate.update(sql, metatag.getName());
        return metatag;
    }

    @Override
    public Metatag update(Metatag metatag) {
        String sql = "UPDATE metatags SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, metatag.getName(), metatag.getId());
        return metatag;
    }
} 