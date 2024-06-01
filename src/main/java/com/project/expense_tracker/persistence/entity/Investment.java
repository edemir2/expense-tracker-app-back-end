package com.project.expense_tracker.persistence.entity;

public class Investment {
    private Long investment_id;
    private Long user_id;
    private Double amount;
    private String type;
    private String date;
    private Double returns;

    // Getters and Setters

    public Long getInvestment_id() {
        return investment_id;
    }

    public void setInvestment_id(Long investment_id) {
        this.investment_id = investment_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getReturns() {
        return returns;
    }

    public void setReturns(Double returns) {
        this.returns = returns;
    }
}
