package com.project.expense_tracker.persistence.entity;

public class Savings {
    private Long savings_id;
    private Long user_id;
    private Double amount;
    private String goal;
    private String start_date;
    private String end_date;

    // Getters and Setters
    public Long getSavings_id() {
        return savings_id;
    }

    public void setSavings_id(Long savings_id) {
        this.savings_id = savings_id;
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

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
