package com.example.point.Enum;

import lombok.Getter;

@Getter
public enum paymentValue {
    
    TOSS("toss"),
    KAKAO("kakao");

    private final String type;

    paymentValue(String type){
        this.type = type;
    }
}
