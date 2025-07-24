package com.example.ImcBeProj.repositories;

import com.example.ImcBeProj.models.ElearningUser;
import com.example.ImcBeProj.models.dtos.ElearningForUserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

//    public List<ElearningForUserDto> getUserElearnings(long userId){
//        String sql = "SELECT e.id as elearning_id,  FROM elearning_users eu " +
//                     "JOIN elearings e ON eu.elearning_id=e.id " +
//                     "where eu.user_id = ?";
//        Optional<List<ElearningForUserDto>> list= jdbcTemplate.query(sql,(rs,rowNum)->{
//            ElearningForUserDto dto = new ElearningForUserDto();
//            dto.setId(rs.getString("elearning_id"));
//        });
//    }
} 