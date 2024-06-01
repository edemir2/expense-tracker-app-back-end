package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.entity.Savings;
import com.project.expense_tracker.persistence.dao.UserDAO;
import com.project.expense_tracker.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userDAO.findById(id);
    }

    @GetMapping("/{id}/totalExpenses")
    public Double getTotalExpensesForUser(@PathVariable Long id) {
        return userDAO.findTotalExpensesForUser(id);
    }

    @GetMapping("/{id}/savingsSummary")
    public List<Savings> getSavingsSummaryForUser(@PathVariable Long id) {
        return userDAO.findSavingsSummaryForUser(id);
    }

    @PostMapping
    public int createUser(@RequestBody User user) {
        return userDAO.save(user);
    }

    @PutMapping("/{id}")
    public int updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUser_id(id);
        return userDAO.update(user);
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable Long id) {
        return userDAO.deleteById(id);
    }
}
