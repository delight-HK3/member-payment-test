package com.example.payment.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.payment.Enum.ResponseFailCode;
import com.example.payment.exception.NoSearchException;
import com.example.payment.model.api.ApiResponse;

@RestControllerAdvice
public class Exceptionhandler {
    
    /**
     * 회원목록 전체 조회 시 존재하지 않는경우
     * , 특정회원 조회 시 존재하지 않는경우 Handler
     * 
     * @param <T>
     * @param e
     * @return
     */
    @ExceptionHandler(NoSearchException.class)
    public <T> ResponseEntity<ApiResponse<T>> NoSearchExceptionHandler(NoSearchException e){
        ResponseFailCode exceptionCode = e.getError();
        return ApiResponse.fail(exceptionCode.getStatus()
                                , exceptionCode.getCode()
                                , exceptionCode.getMessage());
    }
    
}
