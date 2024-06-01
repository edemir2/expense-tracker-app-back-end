package com.project.expense_tracker.persistence.dao;

import com.project.expense_tracker.persistence.entity.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IncomeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class IncomeRowMapper implements RowMapper<Income> {
        @Override
        public Income mapRow(ResultSet rs, int rowNum) throws SQLException {
            Income income = new Income();
            income.setIncome_id(rs.getLong("income_id"));
            income.setUser_id(rs.getLong("user_id"));
            income.setAmount(rs.getDouble("amount"));
            income.setDate(rs.getString("date"));
            income.setSource(rs.getString("source"));
            return income;
        }
    }

    public List<Income> findAll() {
        return jdbcTemplate.query("SELECT * FROM Income", new IncomeRowMapper());
    }

    public Income findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Income WHERE income_id = ?", new Object[]{id}, new IncomeRowMapper());
    }

    public int save(Income income) {
        int result = jdbcTemplate.update("INSERT INTO Income (user_id, amount, date, source) VALUES (?, ?, ?, ?)",
                income.getUser_id(), income.getAmount(), income.getDate(), income.getSource());

        if (result > 0) {
            jdbcTemplate.update("UPDATE User SET budget = budget + ? WHERE user_id = ?",
                    income.getAmount(), income.getUser_id());
        }

        return result;
    }


    public int update(Income income) {
        return jdbcTemplate.update("UPDATE Income SET user_id = ?, amount = ?, date = ?, source = ? WHERE income_id = ?",
                income.getUser_id(), income.getAmount(), income.getDate(), income.getSource(), income.getIncome_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Income WHERE income_id = ?", id);
    }
}
