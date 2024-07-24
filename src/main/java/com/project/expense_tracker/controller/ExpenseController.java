package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.ExpenseDAO;
import com.project.expense_tracker.persistence.dao.BudgetDAO;
import com.project.expense_tracker.persistence.entity.Expense;
import com.project.expense_tracker.persistence.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseDAO expenseDAO;
    @Autowired
    private BudgetDAO budgetDAO;
    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);
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
        logger.info("Received request to create expense for user_id {}: amount = {}", expense.getUser_id(), expense.getAmount());
        int result = expenseDAO.save(expense);
        if (result > 0) {
            Budget budget = budgetDAO.findByUserId(expense.getUser_id());
            if (budget != null) {
                logger.info("Updating budget for user_id {}: current budget = {}, deducting amount = {}",
                        expense.getUser_id(), budget.getBudget_amount(), expense.getAmount());
                budget.setBudget_amount(budget.getBudget_amount() - expense.getAmount());
                budgetDAO.update(budget);
                logger.info("Updated budget for user_id {}: new budget = {}",
                        expense.getUser_id(), budget.getBudget_amount());
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
