package com.example.point.model.payment;

import com.example.point.Enum.paymentValue;
import com.example.point.custom.ValidEnum;

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
    private Integer amount;     // 결제 금액

    @NotNull
    private String orderId;     // 결제 ID

    @NotNull
    private String paymentKey;  // 결제 Key

    @ValidEnum(enumClass = paymentValue.class)
    private String payment;   //  결제 플랫폼
    
}
