package com.example.payment.exception;

import com.example.payment.Enum.ResponseFailCode;

import lombok.Getter;

/**
 * 조회시 결과가 없는경우 Exception
 */
@Getter
public class NoSearchException extends RuntimeException{

    private ResponseFailCode error;

    public NoSearchException(ResponseFailCode e) {
        super(e.getMessage());
        this.error = e;
    }
}
