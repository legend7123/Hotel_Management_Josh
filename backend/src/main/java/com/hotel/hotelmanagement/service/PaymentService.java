package com.hotel.hotelmanagement.service;

import com.hotel.hotelmanagement.exception.PaymentNotFoundException;
import com.hotel.hotelmanagement.model.Payment;
import com.hotel.hotelmanagement.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    public PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository=paymentRepository;
    }

    //get all payments
    public List<Payment> getPayment(){
        return paymentRepository.findAll();
    }

    //get payment by id
    public Payment getPaymentById(Long id){
        return paymentRepository.findById(id)
                .orElseThrow(()-> new PaymentNotFoundException("Payment not found"));
    }

    //get payment by userid
    public List<Payment> getAllPaymentByUserId(Long userId){
        return paymentRepository.getByUserId(userId);
    }

    //create new payment
    public Payment savePayment(Payment payment){
        return paymentRepository.save(payment);
    }

    @Transactional
    //update amount by id
    public Payment updateAmount(Long id,Float amount){
        Payment existingPayment = getPaymentById(id);
        existingPayment.setAmount(amount);
        return existingPayment;
    }

    @Transactional
    //update payment status
    public Payment updateStatus(Long id,Boolean status){
        Payment existingPayment = getPaymentById(id);
        existingPayment.setStatus(status);
        return existingPayment;
    }

    @Transactional
    public Payment updateAmountStatus(Long id,Float amount,Boolean status){
        Payment existingPayment = getPaymentById(id);
        existingPayment.setAmount(amount);
        existingPayment.setStatus(status);
        return existingPayment;
    }

    @Transactional
    //delete a payment
    public void deletePayment(Long id){
        Payment existingPayment = getPaymentById(id);
        paymentRepository.delete(existingPayment);
    }
}
