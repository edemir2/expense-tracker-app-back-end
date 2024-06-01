package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.ExpenseDAO;
import com.project.expense_tracker.persistence.dao.BudgetDAO;
import com.project.expense_tracker.persistence.entity.Expense;
import com.project.expense_tracker.persistence.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@RequestMapping("/calculations")
public class CalculationController {

    @Autowired
    private ExpenseDAO expenseDAO;

    @Autowired
    private BudgetDAO budgetDAO;

    @GetMapping("/categoryTotals")
    public Map<Long, Double> calculateCategoryTotals() {
        List<Expense> expenses = expenseDAO.findAll();
        return expenses.stream().collect(Collectors.groupingBy(
                Expense::getCategory_id,
                Collectors.summingDouble(Expense::getAmount)
        ));
    }

    @GetMapping("/monthlyTotal")
    public Double calculateMonthlyTotal() {
        List<Expense> expenses = expenseDAO.findAll();
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    @GetMapping("/dailyAverage")
    public Double calculateDailyAverage() {
        List<Expense> expenses = expenseDAO.findAll();
        long uniqueDays = expenses.stream()
                .map(expense -> expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .distinct()
                .count();
        return expenses.stream().mapToDouble(Expense::getAmount).sum() / uniqueDays;
    }

    @GetMapping("/highestExpense")
    public Expense findHighestExpense() {
        List<Expense> expenses = expenseDAO.findAll();
        return expenses.stream().max((e1, e2) -> Double.compare(e1.getAmount(), e2.getAmount())).orElse(null);
    }

    @GetMapping("/remainingBudget")
    public Double calculateRemainingBudget() {
        List<Expense> expenses = expenseDAO.findAll();
        List<Budget> budgets = budgetDAO.findAll();
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double totalBudget = budgets.stream().mapToDouble(Budget::getBudget_amount).sum();
        return totalBudget - totalExpenses;
    }
}
