package com.project.expense_tracker.persistence.entity;

import java.util.Date;

public class Expense {
    private Long expense_id;
    private Long user_id;
    private Long category_id;
    private Long payment_method_id;
    private Double amount;
    private Date date;
    private String description;

    // Getters and Setters

    public Long getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(Long expense_id) {
        this.expense_id = expense_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPayment_method_id() {return payment_method_id;}

    public void setPayment_method_id(Long payment_method_id) {this.payment_method_id = payment_method_id;}
}
