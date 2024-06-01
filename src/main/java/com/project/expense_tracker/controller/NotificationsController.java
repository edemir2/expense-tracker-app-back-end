package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.NotificationsDAO;
import com.project.expense_tracker.persistence.entity.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    @Autowired
    private NotificationsDAO notificationsDAO;

    @GetMapping
    public List<Notifications> getAllNotifications() {
        return notificationsDAO.findAll();
    }

    @GetMapping("/{id}")
    public Notifications getNotificationById(@PathVariable Long id) {
        return notificationsDAO.findById(id);
    }

    @PostMapping
    public int createNotification(@RequestBody Notifications notification) {
        return notificationsDAO.save(notification);
    }

    @PutMapping("/{id}")
    public int updateNotification(@PathVariable Long id, @RequestBody Notifications notification) {
        notification.setNotification_id(id);
        return notificationsDAO.update(notification);
    }

    @DeleteMapping("/{id}")
    public int deleteNotification(@PathVariable Long id) {
        return notificationsDAO.deleteById(id);
    }
}
