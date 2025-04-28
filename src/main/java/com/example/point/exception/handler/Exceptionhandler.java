package com.example.point.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.point.Enum.ResponseFailCode;
import com.example.point.exception.ConflictException;
import com.example.point.exception.NoSearchException;
import com.example.point.exception.PaymentFailException;
import com.example.point.model.api.ApiResponse;

@RestControllerAdvice
public class Exceptionhandler {
    
    /**
     * 회원목록 전체 조회 시 존재하지 않는경우
     * , 특정회원 조회 시 존재하지 않는경우 Handler
     * 
     * 회원 관련 Exception
     * 
     * @param <T>
     * @param e
     * @return
     */
    @ExceptionHandler(NoSearchException.class)
    public <T> ResponseEntity<ApiResponse<T>> NoSearchExceptionHandler(NoSearchException e){
        ResponseFailCode exceptionCode = e.getError();
        return ApiResponse.nonBodyMessage(exceptionCode.getStatus()
                                , exceptionCode.getCode()
                                , exceptionCode.getMessage());
    }

    /**
     * 조회시 다른 회원과 충돌이 발생한 경우 Handler
     * 
     * 회원 관련 Exception
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler(ConflictException.class)
    public <T> ResponseEntity<ApiResponse<T>> ConflictExceptionHandler(){
        return ApiResponse.nonBodyMessage(ResponseFailCode.NO_MATCH_METHOD.getStatus()
                                , ResponseFailCode.NO_MATCH_METHOD.getCode()
                                , ResponseFailCode.NO_MATCH_METHOD.getMessage());
    }   

    /**
     * 결제시도시 발생하는 각종 Exception Handler
     * 
     * 결제 관련 Exception
     * 
     * @param <T>
     * @param e
     * @return
     */
    @ExceptionHandler(PaymentFailException.class)
    public <T> ResponseEntity<ApiResponse<T>> PaymentFailExceptionHandler(Exception e){
        return ApiResponse.nonBodyMessage(null
                                        , null
                                        , e.getMessage());
    }

    /**
     * 파라미터의 타입이 일치하지 않는 경우 Handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public <T> ResponseEntity<ApiResponse<T>> MethodArgumentTypeMismatchExceptionHandler(){
        return ApiResponse.nonBodyMessage(ResponseFailCode.NO_TYPE_MISMATCH_ARGUMENT.getStatus()
                                , ResponseFailCode.NO_TYPE_MISMATCH_ARGUMENT.getCode()
                                , ResponseFailCode.NO_TYPE_MISMATCH_ARGUMENT.getMessage());
    }

    /**
     * 파라미터의 값이 올바르지 않는 경우 Handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ResponseEntity<ApiResponse<T>> MethodArgumentNotValidExceptionHandler(){
        return ApiResponse.nonBodyMessage(ResponseFailCode.NO_VALUE_MISMATCH_ARGUMENT.getStatus()
                                , ResponseFailCode.NO_VALUE_MISMATCH_ARGUMENT.getCode()
                                , ResponseFailCode.NO_VALUE_MISMATCH_ARGUMENT.getMessage());
    }

    /**
     * 필수 파라미터의 값이 누락된 경우 handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public <T> ResponseEntity<ApiResponse<T>> MissingPathVariableExceptionHandler(){
        return ApiResponse.nonBodyMessage(ResponseFailCode.NO_REQUIRED_ARGUMENT.getStatus()
                                , ResponseFailCode.NO_REQUIRED_ARGUMENT.getCode()
                                , ResponseFailCode.NO_REQUIRED_ARGUMENT.getMessage());
    }

    /**
     * 존재하지 않는 주소를 요청하는 경우 Handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public <T> ResponseEntity<ApiResponse<T>> NoResourceFoundExceptionHandler(){
        return ApiResponse.nonBodyMessage(ResponseFailCode.NOT_FOUND_PAGE.getStatus()
                                , ResponseFailCode.NOT_FOUND_PAGE.getCode()
                                , ResponseFailCode.NOT_FOUND_PAGE.getMessage());
    }

    /**
     * 요청 URL은 맞지만 일치하지 않는 메서드 타입으로 요청하는 경우 Handler
     * 
     * @param <T>
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public <T> ResponseEntity<ApiResponse<T>> HttpRequestMethodNotSupportedExceptionHandler(){
        return ApiResponse.nonBodyMessage(ResponseFailCode.NO_MATCH_METHOD.getStatus()
                                , ResponseFailCode.NO_MATCH_METHOD.getCode()
                                , ResponseFailCode.NO_MATCH_METHOD.getMessage());
    }

}   
