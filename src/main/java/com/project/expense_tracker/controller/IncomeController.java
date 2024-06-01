package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.IncomeDAO;
import com.project.expense_tracker.persistence.entity.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IncomeDAO incomeDAO;

    @GetMapping
    public List<Income> getAllIncomes() {
        return incomeDAO.findAll();
    }

    @GetMapping("/{id}")
    public Income getIncomeById(@PathVariable Long id) {
        return incomeDAO.findById(id);
    }

    @PostMapping
    public int createIncome(@RequestBody Income income) {
        return incomeDAO.save(income);
    }

    @PutMapping("/{id}")
    public int updateIncome(@PathVariable Long id, @RequestBody Income income) {
        income.setIncome_id(id);
        return incomeDAO.update(income);
    }

    @DeleteMapping("/{id}")
    public int deleteIncome(@PathVariable Long id) {
        return incomeDAO.deleteById(id);
    }
}
