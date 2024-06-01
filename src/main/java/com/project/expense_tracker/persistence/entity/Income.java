package com.project.expense_tracker.persistence.entity;

public class Income {
    private Long income_id;
    private Long user_id;
    private Double amount;
    private String date;
    private String source;

    // Getters and Setters

    public Long getIncome_id() {
        return income_id;
    }

    public void setIncome_id(Long income_id) {
        this.income_id = income_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
