package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.InvestmentDAO;
import com.project.expense_tracker.persistence.entity.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentDAO investmentDAO;

    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentDAO.findAll();
    }

    @GetMapping("/{id}")
    public Investment getInvestmentById(@PathVariable Long id) {
        return investmentDAO.findById(id);
    }

    @PostMapping
    public int createInvestment(@RequestBody Investment investment) {
        return investmentDAO.save(investment);
    }

    @PutMapping("/{id}")
    public int updateInvestment(@PathVariable Long id, @RequestBody Investment investment) {
        investment.setInvestment_id(id);
        return investmentDAO.update(investment);
    }

    @DeleteMapping("/{id}")
    public int deleteInvestment(@PathVariable Long id) {
        return investmentDAO.deleteById(id);
    }
}
