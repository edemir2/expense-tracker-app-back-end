package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.CategoryDAO;
import com.project.expense_tracker.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDAO;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryDAO.findById(id);
    }

    @PostMapping
    public int createCategory(@RequestBody Category category) {
        return categoryDAO.save(category);
    }

    @PutMapping("/{id}")
    public int updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setCategory_id(id);
        return categoryDAO.update(category);
    }

    @DeleteMapping("/{id}")
    public int deleteCategory(@PathVariable Long id) {
        return categoryDAO.deleteById(id);
    }
}
