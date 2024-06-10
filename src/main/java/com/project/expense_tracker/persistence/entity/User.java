package com.project.expense_tracker.persistence.entity;

public class User {
    private Long user_id;
    private String name;
    private String email;
    private String password;
    private Double budget = 0.0;

    // Getters and Setters

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBudget() {return budget;}

    public void setBudget(Double budget) {this.budget = budget;}
}
