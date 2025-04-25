package com.example.point.exception;

import com.example.point.Enum.ResponseFailCode;

import lombok.Getter;

/**
 * 낙관적 락으로 인한 Exception
 */
@Getter
public class ConflictException extends RuntimeException{
    
    private ResponseFailCode error;

    public ConflictException(ResponseFailCode e) {
        super(e.getMessage());
        this.error = e;
    }
}
