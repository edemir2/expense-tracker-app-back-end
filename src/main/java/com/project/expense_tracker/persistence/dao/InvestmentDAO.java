package com.project.expense_tracker.persistence.dao;

import com.project.expense_tracker.persistence.entity.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvestmentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class InvestmentRowMapper implements RowMapper<Investment> {
        @Override
        public Investment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Investment investment = new Investment();
            investment.setInvestment_id(rs.getLong("investment_id"));
            investment.setUser_id(rs.getLong("user_id"));
            investment.setAmount(rs.getDouble("amount"));
            investment.setType(rs.getString("type"));
            investment.setDate(rs.getString("date"));
            investment.setReturns(rs.getDouble("returns"));
            return investment;
        }
    }

    public List<Investment> findAll() {
        return jdbcTemplate.query("SELECT * FROM Investment", new InvestmentRowMapper());
    }

    public Investment findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Investment WHERE investment_id = ?", new Object[]{id}, new InvestmentRowMapper());
    }

    public int save(Investment investment) {
        return jdbcTemplate.update("INSERT INTO Investment (user_id, amount, type, date, returns) VALUES (?, ?, ?, ?, ?)",
                investment.getUser_id(), investment.getAmount(), investment.getType(), investment.getDate(), investment.getReturns());
    }

    public int update(Investment investment) {
        return jdbcTemplate.update("UPDATE Investment SET user_id = ?, amount = ?, type = ?, date = ?, returns = ? WHERE investment_id = ?",
                investment.getUser_id(), investment.getAmount(), investment.getType(), investment.getDate(), investment.getReturns(), investment.getInvestment_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Investment WHERE investment_id = ?", id);
    }
}
