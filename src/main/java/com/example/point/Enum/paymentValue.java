package com.example.point.Enum;

import lombok.Getter;

@Getter
public enum paymentValue {
    
    CARD("card"),
    POINT("point");

    private final String property;

    paymentValue(String property){
        this.property = property;
    }
}
