package com.project.expense_tracker.persistence.dao;

import com.project.expense_tracker.persistence.entity.Savings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SavingsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class SavingsRowMapper implements RowMapper<Savings> {
        @Override
        public Savings mapRow(ResultSet rs, int rowNum) throws SQLException {
            Savings savings = new Savings();
            savings.setSavings_id(rs.getLong("savings_id"));
            savings.setUser_id(rs.getLong("user_id"));
            savings.setAmount(rs.getDouble("amount"));
            savings.setGoal(rs.getString("goal"));
            savings.setStart_date(rs.getString("start_date"));
            savings.setEnd_date(rs.getString("end_date"));
            return savings;
        }
    }

    public List<Savings> findAll() {
        return jdbcTemplate.query("SELECT * FROM Savings", new SavingsRowMapper());
    }

    public Savings findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Savings WHERE savings_id = ?", new Object[]{id}, new SavingsRowMapper());
    }

    public int save(Savings savings) {
        return jdbcTemplate.update("INSERT INTO Savings (user_id, amount, goal, start_date, end_date) VALUES (?, ?, ?, ?, ?)",
                savings.getUser_id(), savings.getAmount(), savings.getGoal(), savings.getStart_date(), savings.getEnd_date());
    }

    public int update(Savings savings) {
        return jdbcTemplate.update("UPDATE Savings SET user_id = ?, amount = ?, goal = ?, start_date = ?, end_date = ? WHERE savings_id = ?",
                savings.getUser_id(), savings.getAmount(), savings.getGoal(), savings.getStart_date(), savings.getEnd_date(), savings.getSavings_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Savings WHERE savings_id = ?", id);
    }
}
