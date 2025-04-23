package com.example.payment.Enum;

import lombok.Getter;

@Getter
public enum orderValue {
    
    NAME("name")
    , VIEWCNT("viewcnt")
    , CREATEDT("createdt")
    , DEFAULT("default");

    private final String property;

    orderValue(String property){
        this.property = property;
    }
}
