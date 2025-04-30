package com.example.point.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.point.exception.NoSearchException;
import com.example.point.model.payment.PaymentRequestDTO;
import com.example.point.service.payment.PaymentService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DisplayName("결제 관련기능 테스트")
public class PaymentServiceTest {
    
    @Autowired
    private PaymentService paymentService;

    @Test
    @DisplayName("조건에 따라 회원목록 조회 테스트 (포인트 정보 없는 경우)")
    public void paymentMemberDisable(){
        // 성공 : 회원번호 5번 사용자는 기존에 없던 사용자이기에 포인트 30000 생성
        // 실패 : exceiption의 메세지 로그 출력
        try{
            PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
            paymentRequestDTO.setAmount(30000);
            paymentRequestDTO.setPayment("toss");

            paymentService.requestPayment(paymentRequestDTO, 5L);
        } catch(NoSearchException e) {
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 사용자
            // 메세지 2 : 존재하지 않는 결제 플랫폼
        }
    }

    @Test
    @DisplayName("조건에 따라 회원목록 조회 테스트 (포인트 정보 있는 경우)")
    public void paymentMemberEnable(){
        // 성공 : 회원번호 1번 사용자는 기존에 존재하는 사용자이기에 기존의 포인트에서 10000증가
        // 실패 : exceiption의 메세지 로그 출력
        try{
            PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
            paymentRequestDTO.setAmount(10000);
            paymentRequestDTO.setPayment("toss");

            paymentService.requestPayment(paymentRequestDTO, 1L);
        } catch(NoSearchException e) {
            log.warn(e.getMessage());
            // 메세지 1 : 존재하지 않는 사용자
            // 메세지 2 : 존재하지 않는 결제 플랫폼
        }
    }

}
