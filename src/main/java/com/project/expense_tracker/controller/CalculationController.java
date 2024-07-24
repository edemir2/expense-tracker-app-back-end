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
    public Map<Long, Double> calculateCategoryTotals(@RequestParam Long userId) {
        List<Expense> expenses = expenseDAO.findAllByUserId(userId);
        return expenses.stream().collect(Collectors.groupingBy(
                Expense::getCategory_id,
                Collectors.summingDouble(Expense::getAmount)
        ));
    }

    @GetMapping("/monthlyTotal")
    public Double calculateMonthlyTotal(@RequestParam Long userId) {
        List<Expense> expenses = expenseDAO.findAllByUserId(userId);
        LocalDate now = LocalDate.now();
        return expenses.stream()
                .filter(expense -> expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth() == now.getMonth())
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @GetMapping("/dailyAverage")
    public Double calculateDailyAverage(@RequestParam Long userId) {
        List<Expense> expenses = expenseDAO.findAllByUserId(userId);
        long uniqueDays = expenses.stream()
                .map(expense -> expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .distinct()
                .count();
        return uniqueDays == 0 ? 0 : expenses.stream().mapToDouble(Expense::getAmount).sum() / uniqueDays;
    }

    @GetMapping("/highestExpense")
    public Expense findHighestExpense(@RequestParam Long userId) {
        List<Expense> expenses = expenseDAO.findAllByUserId(userId);
        return expenses.stream().max((e1, e2) -> Double.compare(e1.getAmount(), e2.getAmount())).orElse(null);
    }

    @GetMapping("/remainingBudget")
    public Double calculateRemainingBudget(@RequestParam Long userId) {
        List<Expense> expenses = expenseDAO.findAllByUserId(userId);
        Budget budget = budgetDAO.findByUserId(userId);
        if (budget == null) {
            return null; // Or handle as appropriate
        }
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        return budget.getBudget_amount() - totalExpenses;
    }


}
