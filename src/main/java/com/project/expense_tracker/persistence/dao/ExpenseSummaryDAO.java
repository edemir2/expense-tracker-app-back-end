package com.project.expense_tracker.persistence.dao;

import com.project.expense_tracker.persistence.entity.ExpenseSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExpenseSummaryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class ExpenseSummaryRowMapper implements RowMapper<ExpenseSummary> {
        @Override
        public ExpenseSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExpenseSummary expenseSummary = new ExpenseSummary();
            expenseSummary.setSummary_id(rs.getLong("summary_id"));
            expenseSummary.setUser_id(rs.getLong("user_id"));
            expenseSummary.setMonth(rs.getString("month"));
            expenseSummary.setTotal_expense(rs.getDouble("total_expense"));
            expenseSummary.setTotal_income(rs.getDouble("total_income"));
            expenseSummary.setNet_savings(rs.getDouble("net_savings"));
            return expenseSummary;
        }
    }

    public List<ExpenseSummary> findAll() {
        return jdbcTemplate.query("SELECT * FROM ExpenseSummary", new ExpenseSummaryRowMapper());
    }

    public ExpenseSummary findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ExpenseSummary WHERE summary_id = ?", new Object[]{id}, new ExpenseSummaryRowMapper());
    }

    public int save(ExpenseSummary expenseSummary) {
        return jdbcTemplate.update("INSERT INTO ExpenseSummary (user_id, month, total_expense, total_income, net_savings) VALUES (?, ?, ?, ?, ?)",
                expenseSummary.getUser_id(), expenseSummary.getMonth(), expenseSummary.getTotal_expense(), expenseSummary.getTotal_income(), expenseSummary.getNet_savings());
    }

    public int update(ExpenseSummary expenseSummary) {
        return jdbcTemplate.update("UPDATE ExpenseSummary SET user_id = ?, month = ?, total_expense = ?, total_income = ?, net_savings = ? WHERE summary_id = ?",
                expenseSummary.getUser_id(), expenseSummary.getMonth(), expenseSummary.getTotal_expense(), expenseSummary.getTotal_income(), expenseSummary.getNet_savings(), expenseSummary.getSummary_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM ExpenseSummary WHERE summary_id = ?", id);
    }
}
