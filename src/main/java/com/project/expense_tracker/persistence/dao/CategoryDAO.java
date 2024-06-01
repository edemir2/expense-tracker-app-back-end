package com.project.expense_tracker.persistence.dao;

import com.project.expense_tracker.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setCategory_id(rs.getLong("category_id"));
            category.setCategory_name(rs.getString("category_name"));
            return category;
        }
    }

    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * FROM Category", new CategoryRowMapper());
    }

    public Category findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Category WHERE category_id = ?", new Object[]{id}, new CategoryRowMapper());
    }

    public int save(Category category) {
        return jdbcTemplate.update("INSERT INTO Category (category_name) VALUES (?)",
                category.getCategory_name());
    }

    public int update(Category category) {
        return jdbcTemplate.update("UPDATE Category SET category_name = ? WHERE category_id = ?",
                category.getCategory_name(), category.getCategory_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Category WHERE category_id = ?", id);
    }
}
