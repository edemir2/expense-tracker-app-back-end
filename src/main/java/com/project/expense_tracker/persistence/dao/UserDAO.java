package com.project.expense_tracker.persistence.dao;


import com.project.expense_tracker.persistence.entity.User;
import com.project.expense_tracker.persistence.entity.Savings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUser_id(rs.getLong("user_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            return user;
        }
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM User", new UserRowMapper());
    }

    public Double findTotalExpensesForUser(Long userId) {
        return jdbcTemplate.queryForObject(
                "SELECT SUM(amount) FROM Expense WHERE user_id = ?",
                new Object[]{userId},
                Double.class
        );
    }

    public List<Savings> findSavingsSummaryForUser(Long userId) {
        return jdbcTemplate.query(
                "SELECT * FROM Savings WHERE user_id = ?",
                new Object[]{userId},
                new RowMapper<Savings>() {
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
        );
    }



    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM User WHERE user_id = ?", new Object[]{id}, new UserRowMapper());
    }

    public int save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO User (name, email, password) VALUES (?, ?, ?)", new String[] {"user_id"});
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                return ps;
            }
        }, keyHolder);

        if (rowsAffected > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return 0;  // or handle as appropriate
        }
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE User SET name = ?, email = ?, password = ? WHERE user_id = ?",
                user.getName(), user.getEmail(), user.getPassword(), user.getUser_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM User WHERE user_id = ?", id);
    }
}
