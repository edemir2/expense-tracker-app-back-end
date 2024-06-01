package com.project.expense_tracker.persistence.entity;

public class ExpenseSummary {
    private Long summary_id;
    private Long user_id;
    private String month;
    private Double total_expense;
    private Double total_income;
    private Double net_savings;

    // Getters and Setters

    public Long getSummary_id() {
        return summary_id;
    }

    public void setSummary_id(Long summary_id) {
        this.summary_id = summary_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTotal_expense() {
        return total_expense;
    }

    public void setTotal_expense(Double total_expense) {
        this.total_expense = total_expense;
    }

    public Double getTotal_income() {
        return total_income;
    }

    public void setTotal_income(Double total_income) {
        this.total_income = total_income;
    }

    public Double getNet_savings() {
        return net_savings;
    }

    public void setNet_savings(Double net_savings) {
        this.net_savings = net_savings;
    }
}
