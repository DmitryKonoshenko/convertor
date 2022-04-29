package com.divanxan.convertor.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyException extends Exception {
    private String errorMessage;

    public CurrencyException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
