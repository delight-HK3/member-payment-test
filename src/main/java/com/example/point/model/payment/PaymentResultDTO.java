package com.example.point.model.payment;

import lombok.Getter;

/**
 * 결제 결과 DTO객체
 */
@Getter
public class PaymentResultDTO {
    
    private int code;           // 결제 결과 코드
    private String message;     // 결제 결과 메세지
    
    public PaymentResultDTO(int code, String message){
        this.code = code;
        this.message = message;
    }
}
