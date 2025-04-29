package com.example.point.exception;

import lombok.Getter;

/**
 * DTO 파라미터중 필수 Enum값이 없는경우 Exception
 */
@Getter
public class NoEnumArgException extends RuntimeException {

    public NoEnumArgException() { }

}
