package com.example.point.service.payment.paytype;

import org.springframework.stereotype.Service;

import com.example.point.Enum.paymentValue;
import com.example.point.domain.Member;
import com.example.point.model.payment.PaymentRequestDTO;
import com.example.point.model.payment.PaymentResultDTO;

@Service
public interface PaymentTypeService {

    paymentValue getPaymentValue(); // 결제하는 서비스 타입

    PaymentResultDTO getPaymentRedirectURL(PaymentRequestDTO paymentRequestDTO) throws Exception ; // 결제서비스에 요청

    void savePayment(Member member,PaymentRequestDTO paymentRequestDTO, PaymentResultDTO paymentResultDTO); // 결제 후 포인트 및 결제기록 저장 

}