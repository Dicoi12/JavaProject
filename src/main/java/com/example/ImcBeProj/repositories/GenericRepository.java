package com.example.ImcBeProj.repositories;

import com.example.ImcBeProj.repositories.interfaces.IGenericRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public abstract class GenericRepository<T,ID> implements IGenericRepository<T,ID> {
    protected final JdbcTemplate jdbcTemplate;
    protected final String tableName;
    protected final RowMapper<T> rowMapper;

    public GenericRepository(JdbcTemplate jdbcTemplate, String tableName, RowMapper<T> rowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = tableName;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<T>findAll(){
        String sql = "SELECT * FROM "+tableName;
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Optional<T> findById(ID id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        List<T> results = jdbcTemplate.query(sql, rowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public void deleteById(ID id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
