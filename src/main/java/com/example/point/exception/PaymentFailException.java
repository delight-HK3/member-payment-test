package com.example.point.exception;

import com.example.point.Enum.ResponseFailCode;

import lombok.Getter;

/**
 * 결제 시도 및 실패시 Exception
 */
@Getter
public class PaymentFailException extends Exception{

    private ResponseFailCode error;

    public PaymentFailException(Exception e) {
        super(e.getMessage());
    }
}
