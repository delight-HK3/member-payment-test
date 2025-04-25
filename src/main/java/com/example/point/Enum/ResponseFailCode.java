package com.example.point.Enum;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

/**
 * Exception이 발생한 경우 출력될 HttpStatus, HttpCode, 상태 메세지
 */
@Getter
public enum ResponseFailCode {
    
    // 회원관련 메세지
    NO_SEARCH_MEMBER(HttpStatus.NOT_FOUND ,404 ,"This member does not exist."),     // 존재하지 않는 회원 입니다.
    MEMBER_EMPTY(HttpStatus.NOT_FOUND ,404 ,"There is no member list"),             // 회원목록이 없습니다.
    
    // 데이터베이스 관련 메세지
    DATABASE_CONFLICT(HttpStatus.CONFLICT, 409, "There has been a conflict with another user. Please try terrorizing again."),   // 다른 사용자와 충돌이 발생했습니다. 잠시 후 다시 시도해주세요.

    // 클라이언트 요청관련 메세지
    NO_REQUIRED_ARGUMENT(HttpStatus.BAD_REQUEST,400,"A required parameter is missing."),                // 필수 파라미터가 누락되었습니다.
    NO_VALUE_MISMATCH_ARGUMENT(HttpStatus.BAD_REQUEST,400,"The value of the parameter is incorrect."),  // 파라미터의 값이 올바르지 않습니다.
    NO_TYPE_MISMATCH_ARGUMENT(HttpStatus.BAD_REQUEST,400,"The parameter type is incorrect."),           // 파라미터 타입이 올바르지 않습니다.
    NOT_FOUND_PAGE(HttpStatus.NOT_FOUND, 404, "The request does not exist."),                           // 존재하지 않는 요청입니다.
    NO_MATCH_METHOD(HttpStatus.METHOD_NOT_ALLOWED, 405, "This method is not allowed.");                 // 허용되지 않는 메서드 입니다.

    private final HttpStatusCode status; // http 상태
    private final int code;              // http 상태 코드
    private final String message;        // 작업 후 메세지

    ResponseFailCode(HttpStatus status, int code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
