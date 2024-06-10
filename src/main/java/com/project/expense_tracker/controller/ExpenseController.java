package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.ExpenseDAO;
import com.project.expense_tracker.persistence.dao.BudgetDAO;
import com.project.expense_tracker.persistence.dao.UserDAO;
import com.project.expense_tracker.persistence.entity.Expense;
import com.project.expense_tracker.persistence.entity.User;
import com.project.expense_tracker.persistence.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseDAO expenseDAO;
    @Autowired
    private BudgetDAO budgetDAO;
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
        int result = expenseDAO.save(expense);
        if (result > 0) {
            Budget budget = budgetDAO.findByUserId(expense.getUser_id());
            if (budget != null) {
                budget.setBudget_amount(budget.getBudget_amount() - expense.getAmount());
                budgetDAO.update(budget);
            }
        }
        return result;
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
