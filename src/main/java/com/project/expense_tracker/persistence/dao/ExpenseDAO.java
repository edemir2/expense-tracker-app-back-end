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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Repository
public class ExpenseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ExpenseDAO.class);
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
    public List<Expense> findAllByUserId(Long userId) {
        return jdbcTemplate.query("SELECT * FROM Expense WHERE user_id = ?", new Object[]{userId}, new ExpenseRowMapper());
    }

    public Expense findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Expense WHERE expense_id = ?", new Object[]{id}, new ExpenseRowMapper());
    }

    public int save(Expense expense) {
        logger.info("Saving expense for user_id {}: amount = {}", expense.getUser_id(), expense.getAmount());
        return jdbcTemplate.update("INSERT INTO Expense (user_id, category_id, payment_method_id, amount, date, description) VALUES (?, ?, ?, ?, ?, ?)",
                expense.getUser_id(), expense.getCategory_id(), expense.getPayment_method_id(), expense.getAmount(), expense.getDate(), expense.getDescription());
    }


    public int update(Expense expense) {
        return jdbcTemplate.update("UPDATE Expense SET user_id = ?, category_id = ?, amount = ?, date = ?, description = ? WHERE expense_id = ?",
                expense.getUser_id(), expense.getCategory_id(), expense.getAmount(), expense.getDate(), expense.getDescription(), expense.getExpense_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Expense WHERE expense_id = ?", id);
    }
}
