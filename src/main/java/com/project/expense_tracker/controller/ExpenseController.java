package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.ExpenseDAO;
import com.project.expense_tracker.persistence.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseDAO expenseDAO;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseDAO.findAll();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseDAO.findById(id);
    }

    @PostMapping
    public int createExpense(@RequestBody Expense expense) {
        return expenseDAO.save(expense);
    }

    @PutMapping("/{id}")
    public int updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        expense.setExpense_id(id);
        return expenseDAO.update(expense);
    }

    @DeleteMapping("/{id}")
    public int deleteExpense(@PathVariable Long id) {
        return expenseDAO.deleteById(id);
    }
}
