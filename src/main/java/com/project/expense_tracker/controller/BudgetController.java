package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.BudgetDAO;
import com.project.expense_tracker.persistence.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetDAO budgetDAO;

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetDAO.findAll();
    }

    @GetMapping("/{id}")
    public Budget getBudgetById(@PathVariable Long id) {
        return budgetDAO.findById(id);
    }

    @PostMapping
    public int createBudget(@RequestBody Budget budget) {
        return budgetDAO.save(budget);
    }

    @PutMapping("/{id}")
    public int updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        budget.setBudget_id(id);
        return budgetDAO.update(budget);
    }

    @DeleteMapping("/{id}")
    public int deleteBudget(@PathVariable Long id) {
        return budgetDAO.deleteById(id);
    }
}
