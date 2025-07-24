package com.example.ImcBeProj.repositories;

import com.example.ImcBeProj.models.ElearningMetatag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ElearningMetatagRepository extends GenericRepository<ElearningMetatag, Integer> {
    private static final RowMapper<ElearningMetatag> rowMapper = (rs, rowNum) -> {
        return new ElearningMetatag(
            rs.getInt("id"),
            rs.getString("elearning_id"),
            rs.getInt("metatag_id")
        );
    };

    public ElearningMetatagRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "elearning_metatags", rowMapper);
    }

    @Override
    public ElearningMetatag save(ElearningMetatag em) {
        String sql = "INSERT INTO elearning_metatags (elearning_id, metatag_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, em.getElearningId(), em.getMetatagId());
        return em;
    }

    @Override
    public ElearningMetatag update(ElearningMetatag em) {
        String sql = "UPDATE elearning_metatags SET elearning_id=?, metatag_id=? WHERE id=?";
        jdbcTemplate.update(sql, em.getElearningId(), em.getMetatagId(), em.getId());
        return em;
    }
} 