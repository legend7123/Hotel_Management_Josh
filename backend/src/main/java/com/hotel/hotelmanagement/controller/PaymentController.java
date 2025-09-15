package com.hotel.hotelmanagement.controller;

import com.hotel.hotelmanagement.dto.ErrorResponseDto;
import com.hotel.hotelmanagement.dto.PaymentRequestDTO;
import com.hotel.hotelmanagement.dto.ResponseDto;
import com.hotel.hotelmanagement.model.Payment;
import com.hotel.hotelmanagement.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    public PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllPayments(){
        List<Payment> payments = paymentService.getPayment();
        ResponseDto response = new ResponseDto(200,payments);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getPaymentById(@PathVariable Long id){
        Payment existingPayment = paymentService.getPaymentById(id);
        ResponseDto response = new ResponseDto(200,existingPayment);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDto> getPaymentByUserId(@PathVariable Long userId){
        List<Payment> payments = paymentService.getAllPaymentByUserId(userId);
        ResponseDto response = new ResponseDto(200,payments);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createPayment(@RequestBody PaymentRequestDTO dto){
        Payment payment = new Payment(dto.getUserId(), dto.getAmount(), dto.getStatus());
        Payment newPayment = paymentService.savePayment(payment);
        ResponseDto response = new ResponseDto(201,newPayment);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/amount")
    public ResponseEntity<ResponseDto> updateAmount(@PathVariable Long id,@RequestParam Float amount){
        Payment updatedPayment = paymentService.updateAmount(id,amount);
        ResponseDto response = new ResponseDto(200,updatedPayment);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDto> updateStatus(@PathVariable Long id,@RequestParam Boolean status){
        Payment updatedPayment = paymentService.updateStatus(id,status);
        ResponseDto response = new ResponseDto(200,updatedPayment);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updatePayment(@PathVariable Long id,@RequestBody PaymentRequestDTO dto){
        Payment updatedPayment = paymentService.updateAmountStatus(id, dto.getAmount(), dto.getStatus());
        ResponseDto response = new ResponseDto(200,updatedPayment);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        ResponseDto response = new ResponseDto(204,"Payment Deleted");
        return ResponseEntity.ok(response);
    }
}
