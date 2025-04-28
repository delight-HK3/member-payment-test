package com.example.point.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.example.point.Enum.paymentValue;

@Configuration
public class paymentValueConverter implements Converter<String, paymentValue>{
    
    @Override
    public paymentValue convert(String s){
        return paymentValue.valueOf(s.toUpperCase());
    }

}
