package com.example.point.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.example.point.Enum.orderValue;

@Configuration
public class orderValueConverter implements Converter<String, orderValue>{

    @Override
    public orderValue convert(String s) {
        // 입력받은 문자열을 모두 대문자로 변경
        return orderValue.valueOf(s.toUpperCase());
    }
}
