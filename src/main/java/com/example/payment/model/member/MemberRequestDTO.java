package com.example.payment.model.member;

import com.example.payment.Enum.orderValue;

import lombok.Getter;
import lombok.Setter;

/**
 * 회원 조회시 Request용도 DTO 객체
 */
@Getter
@Setter
public class MemberRequestDTO {
    
    private orderValue sort;
    private int pageno = 1;

}
