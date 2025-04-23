package com.example.payment.Enum;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

/**
 * Exception이 발생한 경우 출력될 HttpStatus, HttpCode, 상태 메세지
 */
@Getter
public enum ResponseFailCode {
    
    // 고객관련 메세지
    NO_SEARCH_MEMBER(HttpStatus.NOT_FOUND ,404 ,"This member does not exist."), // 존재하지 않는 회원 입니다.
    MEMBER_EMPTY(HttpStatus.NOT_FOUND ,404 ,"There is no member list");         // 회원목록이 없습니다.
    
    private final HttpStatusCode status; // http 상태
    private final int code;              // http 상태 코드
    private final String message;        // 작업 후 메세지

    ResponseFailCode(HttpStatus status, int code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
