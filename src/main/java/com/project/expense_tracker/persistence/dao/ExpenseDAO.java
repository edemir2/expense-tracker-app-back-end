package com.project.expense_tracker.persistence.dao;

import com.project.expense_tracker.persistence.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@Repository
public class ExpenseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class ExpenseRowMapper implements RowMapper<Expense> {
        @Override
        public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
            Expense expense = new Expense();
            expense.setExpense_id(rs.getLong("expense_id"));
            expense.setUser_id(rs.getLong("user_id"));
            expense.setCategory_id(rs.getLong("category_id"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setDate(rs.getDate("date")); // Use Date type
            expense.setDescription(rs.getString("description"));
            return expense;
        }
    }

    public List<Expense> findAll() {
        return jdbcTemplate.query("SELECT * FROM Expense", new ExpenseRowMapper());
    }

    public Expense findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Expense WHERE expense_id = ?", new Object[]{id}, new ExpenseRowMapper());
    }

    public int save(Expense expense) {
        int result = jdbcTemplate.update("INSERT INTO Expense (user_id, category_id, amount, date, description, payment_method_id) VALUES (?, ?, ?, ?, ?, ?)",
                expense.getUser_id(), expense.getCategory_id(), expense.getAmount(), expense.getDate(), expense.getDescription(), expense.getPayment_method_id());

        if (result > 0) {
            jdbcTemplate.update("UPDATE User SET budget = budget - ? WHERE user_id = ?",
                    expense.getAmount(), expense.getUser_id());
        }

        return result;
    }


    public int update(Expense expense) {
        return jdbcTemplate.update("UPDATE Expense SET user_id = ?, category_id = ?, amount = ?, date = ?, description = ? WHERE expense_id = ?",
                expense.getUser_id(), expense.getCategory_id(), expense.getAmount(), expense.getDate(), expense.getDescription(), expense.getExpense_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Expense WHERE expense_id = ?", id);
    }
}
