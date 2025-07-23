package com.example.ImcBeProj.repositories;

import com.example.ImcBeProj.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll(){
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findByUsername(String username) {
        try{
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                username
            );
            return users.stream().findFirst();
        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public int save(User user){
        return jdbcTemplate.update("INSERT INTO users (username, password) VALUES (?, ?)", user.getUsername(), user.getPassword());
    }

    public void deleteById(Long id){
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
    
}
