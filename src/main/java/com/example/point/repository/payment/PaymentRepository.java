package com.example.point.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.point.domain.Paymentlog;

@Repository
public interface PaymentRepository extends JpaRepository<Paymentlog, Long>{
    
}
