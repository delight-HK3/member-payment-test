package com.example.point.model.payment;

import com.example.point.Enum.paymentValue;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 결제 요청시 Request용 DTO객체
 */
@Getter
@Setter
public class PaymentRequestDTO {
    
    @NotNull
    private paymentValue payment;   // 결제 수단
    
    @NotNull
    private int memberid;   // 회원 ID

    @NotNull
    private int amount;     // 결제 금액

}
