package com.hotel.hotelmanagement.repository;

import com.hotel.hotelmanagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> getByUserId(Long userId);
}
