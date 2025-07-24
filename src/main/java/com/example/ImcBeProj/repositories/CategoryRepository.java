package com.example.ImcBeProj.repositories;

import com.example.ImcBeProj.models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository extends GenericRepository<Category, Short> {
    private static final RowMapper<Category> rowMapper = (rs, rowNum) -> {
        Category category = new Category();
        category.setId(rs.getShort("id"));
        category.setName(rs.getString("name"));
        return category;
    };

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "elearning_categories", rowMapper);
    }

    @Override
    public Category save(Category category) {
        String sql = "INSERT INTO elearning_categories (name) VALUES (?)";
        jdbcTemplate.update(sql, category.getName());
        return category;
    }

    @Override
    public Category update(Category category) {
        String sql = "UPDATE elearning_categories SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, category.getName(), category.getId());
        return category;
    }
}
