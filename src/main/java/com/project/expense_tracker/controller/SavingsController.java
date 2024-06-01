package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.SavingsDAO;
import com.project.expense_tracker.persistence.entity.Savings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/savings")
public class SavingsController {

    @Autowired
    private SavingsDAO savingsDAO;

    @GetMapping
    public List<Savings> getAllSavings() {
        return savingsDAO.findAll();
    }

    @GetMapping("/{id}")
    public Savings getSavingsById(@PathVariable Long id) {
        return savingsDAO.findById(id);
    }

    @PostMapping
    public int createSavings(@RequestBody Savings savings) {
        return savingsDAO.save(savings);
    }

    @PutMapping("/{id}")
    public int updateSavings(@PathVariable Long id, @RequestBody Savings savings) {
        savings.setSavings_id(id);
        return savingsDAO.update(savings);
    }

    @DeleteMapping("/{id}")
    public int deleteSavings(@PathVariable Long id) {
        return savingsDAO.deleteById(id);
    }
}
