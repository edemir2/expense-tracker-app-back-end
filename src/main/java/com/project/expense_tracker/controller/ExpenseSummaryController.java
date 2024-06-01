package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.ExpenseSummaryDAO;
import com.project.expense_tracker.persistence.entity.ExpenseSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenseSummaries")
public class ExpenseSummaryController {

    @Autowired
    private ExpenseSummaryDAO expenseSummaryDAO;

    @GetMapping
    public List<ExpenseSummary> getAllExpenseSummaries() {
        return expenseSummaryDAO.findAll();
    }

    @GetMapping("/{id}")
    public ExpenseSummary getExpenseSummaryById(@PathVariable Long id) {
        return expenseSummaryDAO.findById(id);
    }

    @PostMapping
    public int createExpenseSummary(@RequestBody ExpenseSummary expenseSummary) {
        return expenseSummaryDAO.save(expenseSummary);
    }

    @PutMapping("/{id}")
    public int updateExpenseSummary(@PathVariable Long id, @RequestBody ExpenseSummary expenseSummary) {
        expenseSummary.setSummary_id(id);
        return expenseSummaryDAO.update(expenseSummary);
    }

    @DeleteMapping("/{id}")
    public int deleteExpenseSummary(@PathVariable Long id) {
        return expenseSummaryDAO.deleteById(id);
    }
}
