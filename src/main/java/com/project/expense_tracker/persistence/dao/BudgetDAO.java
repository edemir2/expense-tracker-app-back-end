package com.project.expense_tracker.persistence.dao;

import com.project.expense_tracker.persistence.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@Repository
public class BudgetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class BudgetRowMapper implements RowMapper<Budget> {
        @Override
        public Budget mapRow(ResultSet rs, int rowNum) throws SQLException {
            Budget budget = new Budget();
            budget.setBudget_id(rs.getLong("budget_id"));
            budget.setUser_id(rs.getLong("user_id"));
            budget.setCategory_id(rs.getLong("category_id"));
            budget.setBudget_amount(rs.getDouble("budget_amount"));
            budget.setStart_date(rs.getDate("start_date")); // Use Date type
            budget.setEnd_date(rs.getDate("end_date")); // Use Date type
            return budget;
        }
    }

    public List<Budget> findAll() {
        return jdbcTemplate.query("SELECT * FROM Budget", new BudgetRowMapper());
    }

    public Budget findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Budget WHERE budget_id = ?", new Object[]{id}, new BudgetRowMapper());
    }

    public int save(Budget budget) {
        return jdbcTemplate.update("INSERT INTO Budget (user_id, category_id, budget_amount, start_date, end_date) VALUES (?, ?, ?, ?, ?)",
                budget.getUser_id(), budget.getCategory_id(), budget.getBudget_amount(), budget.getStart_date(), budget.getEnd_date());
    }

    public int update(Budget budget) {
        return jdbcTemplate.update("UPDATE Budget SET user_id = ?, category_id = ?, budget_amount = ?, start_date = ?, end_date = ? WHERE budget_id = ?",
                budget.getUser_id(), budget.getCategory_id(), budget.getBudget_amount(), budget.getStart_date(), budget.getEnd_date(), budget.getBudget_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Budget WHERE budget_id = ?", id);
    }
}
