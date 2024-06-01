package com.project.expense_tracker.controller;

import com.project.expense_tracker.persistence.dao.PaymentMethodDAO;
import com.project.expense_tracker.persistence.entity.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentMethods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodDAO paymentMethodDAO;

    @GetMapping
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodDAO.findAll();
    }

    @GetMapping("/{id}")
    public PaymentMethod getPaymentMethodById(@PathVariable Long id) {
        return paymentMethodDAO.findById(id);
    }

    @PostMapping
    public int createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodDAO.save(paymentMethod);
    }

    @PutMapping("/{id}")
    public int updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethod paymentMethod) {
        paymentMethod.setPayment_method_id(id);
        return paymentMethodDAO.update(paymentMethod);
    }

    @DeleteMapping("/{id}")
    public int deletePaymentMethod(@PathVariable Long id) {
        return paymentMethodDAO.deleteById(id);
    }
}
