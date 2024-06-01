package com.project.expense_tracker.persistence.dao;

import com.project.expense_tracker.persistence.entity.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NotificationsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class NotificationsRowMapper implements RowMapper<Notifications> {
        @Override
        public Notifications mapRow(ResultSet rs, int rowNum) throws SQLException {
            Notifications notification = new Notifications();
            notification.setNotification_id(rs.getLong("notification_id"));
            notification.setUser_id(rs.getLong("user_id"));
            notification.setMessage(rs.getString("message"));
            notification.setDate(rs.getString("date"));
            return notification;
        }
    }

    public List<Notifications> findAll() {
        return jdbcTemplate.query("SELECT * FROM Notifications", new NotificationsRowMapper());
    }

    public Notifications findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Notifications WHERE notification_id = ?", new Object[]{id}, new NotificationsRowMapper());
    }

    public int save(Notifications notification) {
        return jdbcTemplate.update("INSERT INTO Notifications (user_id, message, date) VALUES (?, ?, ?)",
                notification.getUser_id(), notification.getMessage(), notification.getDate());
    }

    public int update(Notifications notification) {
        return jdbcTemplate.update("UPDATE Notifications SET user_id = ?, message = ?, date = ? WHERE notification_id = ?",
                notification.getUser_id(), notification.getMessage(), notification.getDate(), notification.getNotification_id());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Notifications WHERE notification_id = ?", id);
    }
}
