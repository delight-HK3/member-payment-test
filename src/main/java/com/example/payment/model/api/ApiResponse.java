package com.example.payment.model.api;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

/**
 * API Result
 * API 요청시 응답에 사용하는 객체
 */
@Getter
public class ApiResponse<T> {

    private Response<T> response;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL) 
    public static class Response<T>{
        
        private Header header;
        private Body<T> body;
    
        public Response(Header header, Body<T> body) {
            this.header = header;
            this.body = body;
        }

        public Response(Header header) {
            this.header = header;
        }
    } 

    // api header 부분
    @Getter
    public static class Header{
        private final int code;
        private final String message;

        public Header(int code, String message){
            this.code = code;
            this.message = message;
        }
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public static class Body<T>{
        private T data;
        private int pageNo;

        public Body(T data, int pageNo){
            this.data = data;
            this.pageNo = pageNo;
        }

        public Body(T data){
            this.data = data;
        }
    }

    public ApiResponse(Response<T> response){
        this.response = response;
    }

    // API 리스트 조회 결과 출력 (조회 성공)
    public static <T> ResponseEntity<ApiResponse<T>> successList(HttpStatusCode status, int code, String message , T body, int pageNo) {
        return ResponseEntity.status(status)
                                .body(new ApiResponse<T>(
                                    new Response<T>(
                                        new Header(code, message)
                                        , new Body<T>(body, pageNo)
                                    )
                                ));
    }

    // API 상세조회 결과 출력 (조회 성공)
    public static <T> ResponseEntity<ApiResponse<T>> successDetail(HttpStatusCode status, int code, String message , T body) {
        return ResponseEntity.status(status)
                                .body(new ApiResponse<T>(
                                    new Response<T>(
                                        new Header(code, message)
                                        , new Body<T>(body)
                                    )
                                ));
    }

    // API 조회 결과 출력 (조회 실패)
    public static <T> ResponseEntity<ApiResponse<T>> fail(HttpStatusCode status, int code, String message) {
        return ResponseEntity.status(status)
                                .body(new ApiResponse<T>(
                                    new Response<T>(
                                        new Header(code, message)
                                    )
                                ));
    }

}
