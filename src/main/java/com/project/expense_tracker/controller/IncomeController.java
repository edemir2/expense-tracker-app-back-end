package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.IncomeDAO;
import com.project.expense_tracker.persistence.dao.BudgetDAO;
import com.project.expense_tracker.persistence.entity.Income;
import com.project.expense_tracker.persistence.entity.Budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IncomeDAO incomeDAO;
    @Autowired
    private BudgetDAO budgetDAO;

    private static final Logger logger = LoggerFactory.getLogger(IncomeController.class);

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
        logger.info("Received request to create income for user_id {}: amount = {}", income.getUser_id(), income.getAmount());
        int result = incomeDAO.save(income);
        if (result > 0) {
            Budget budget = budgetDAO.findByUserId(income.getUser_id());
            if (budget != null) {
                logger.info("Updating budget for user_id {}: current budget = {}, adding amount = {}",
                        income.getUser_id(), budget.getBudget_amount(), income.getAmount());
                budget.setBudget_amount(budget.getBudget_amount() + income.getAmount());
                budgetDAO.update(budget);
                logger.info("Updated budget for user_id {}: new budget = {}",
                        income.getUser_id(), budget.getBudget_amount());
            }
        }
        return result;
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
