package com.example.point.Enum;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

/**
 * 작업에 성공 할 경우 출력될 HttpStatus, HttpCode, 상태 메세지
 */
@Getter
public enum ResponseSuccessCode {
    
    SUCCESS_GET(HttpStatus.OK , "SUCCESS_GET", "successfully get"),           // 성공적으로 조회 완료
    SUCCESS_POST(HttpStatus.CREATED , "SUCCESS_POST","successfully post");    // 성공적으로 생성 완료 

    private final HttpStatusCode status; // http 상태
    private final String code;           // http 상태 코드
    private final String message;        // 작업 후 메세지

    ResponseSuccessCode(HttpStatusCode status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
