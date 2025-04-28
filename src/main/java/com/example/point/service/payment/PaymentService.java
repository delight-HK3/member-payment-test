package com.example.point.service.payment;

import org.springframework.stereotype.Service;

import com.example.point.repository.payment.PaymentRepository;
import com.example.point.repository.point.PointRepository;

/**
 * 결제관련 Service
 */
@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final PointRepository pointRepository;

    public PaymentService(PaymentRepository paymentRepository, PointRepository pointRepository){
        this.paymentRepository = paymentRepository;
        this.pointRepository = pointRepository;
    }

}
