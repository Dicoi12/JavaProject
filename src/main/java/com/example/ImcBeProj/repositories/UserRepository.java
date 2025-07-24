package com.example.ImcBeProj.repositories;

import com.example.ImcBeProj.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends GenericRepository<User, Long> {
    private static final RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    public UserRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "users", rowMapper);
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
        return user;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getId());
        return user;
    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, rowMapper, username);
        return users.stream().findFirst();
    }
}
