package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.entity.Savings;
import com.project.expense_tracker.persistence.dao.UserDAO;
import com.project.expense_tracker.persistence.dao.BudgetDAO;
import com.project.expense_tracker.persistence.entity.User;
import com.project.expense_tracker.persistence.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BudgetDAO budgetDAO;

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
        int userId = userDAO.save(user);
        if (userId > 0) {
            // Create a budget record for the new user
            Budget budget = new Budget();
            budget.setUser_id((long) userId);  // Cast userId to Long
            budget.setBudget_amount(0.0);
            budget.setStart_date(new Date()); // Setting the start date to the current date
            Calendar cal = Calendar.getInstance();
            cal.set(9999, 11, 31); // Setting a far future end date
            budget.setEnd_date(cal.getTime());
            budgetDAO.save(budget);
        }
        return userId > 0 ? 1 : 0;
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
