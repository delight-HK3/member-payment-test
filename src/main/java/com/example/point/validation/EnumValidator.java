package com.example.point.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.point.Enum.ResponseFailCode;
import com.example.point.custom.ValidEnum;
import com.example.point.exception.NoEnumArgException;
import com.example.point.exception.NoSearchException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, String>{

    private List<String> enumValues;
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        enumValues = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if(value == null){
            throw new NoEnumArgException();
        }

        if (!enumValues.contains(value.toUpperCase())) {
            if (enumClass.getSimpleName().equals("paymentValue")) {
                throw new NoSearchException(ResponseFailCode.NO_SEARCH_PAYMENT);
            } else if (enumClass.getSimpleName().equals("paymentTypeValue")) {
                throw new NoSearchException(ResponseFailCode.NO_SEARCH_PAYMENT_TYPE);
            } else {
                return false;
            }
        } 
        return true;
    }
}
