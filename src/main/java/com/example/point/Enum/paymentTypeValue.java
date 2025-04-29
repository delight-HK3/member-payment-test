package com.example.point.Enum;

import lombok.Getter;

@Getter
public enum paymentTypeValue {
    
    CARD("card"),
    POINT("point");

    private final String type;

    paymentTypeValue(String type){
        this.type = type;
    }

}
