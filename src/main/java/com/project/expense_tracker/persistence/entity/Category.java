package com.project.expense_tracker.persistence.entity;

public class Category {
    private Long category_id;
    private String category_name;

    // Getters and Setters

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
