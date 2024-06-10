package com.project.expense_tracker.persistence.entity;

import java.util.Date;

public class Budget {
    private Long budget_id;
    private Long user_id;
    private Double budget_amount;
    private Date start_date;
    private Date end_date;

    // Getters and Setters

    public Long getBudget_id() {
        return budget_id;
    }

    public void setBudget_id(Long budget_id) {
        this.budget_id = budget_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }


    public Double getBudget_amount() {
        return budget_amount;
    }

    public void setBudget_amount(Double budget_amount) {
        this.budget_amount = budget_amount;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
