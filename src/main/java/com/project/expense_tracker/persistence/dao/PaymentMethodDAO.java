package com.project.expense_tracker.persistence.dao;

import com.project.expense_tracker.persistence.entity.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PaymentMethodDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class PaymentMethodRowMapper implements RowMapper<PaymentMethod> {
        @Override
        public PaymentMethod mapRow(ResultSet rs, int rowNum) throws SQLException {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setPayment_method_id(rs.getLong("payment_method_id"));
            paymentMethod.setUser_id(rs.getLong("user_id"));
            paymentMethod.setMethod_name(rs.getString("method_name"));
            paymentMethod.setDetails(rs.getString("details"));
            return paymentMethod;
        }
    }

    public List<PaymentMethod> findAll() {
        return jdbcTemplate.query("SELECT * FROM PaymentMethod", new PaymentMethodRowMapper());
    }

    public PaymentMethod findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM PaymentMethod WHERE payment_method_id = ?", new Object[]{id}, new PaymentMethodRowMapper());
    }

    public int save(PaymentMethod paymentMethod) {
        return jdbcTemplate.update("INSERT INTO PaymentMethod (user_id, method_name, details) VALUES (?, ?, ?)",
                paymentMethod.getUser_id(), paymentMethod.getMethod_name(), paymentMethod.getDetails());
    }

    public int update(PaymentMethod paymentMethod) {
        return jdbcTemplate.update("UPDATE PaymentMethod SET user_id = ?, method_name = ?, details = ? WHERE payment_method_id = ?",
                paymentMethod.getUser_id(), paymentMethod.getMethod_name(), paymentMethod.getDetails(), paymentMethod.getPayment_method_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM PaymentMethod WHERE payment_method_id = ?", id);
    }
}
