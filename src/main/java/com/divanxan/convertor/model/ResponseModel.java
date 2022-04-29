package com.divanxan.convertor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

    private String currency;
    private String resultCurrency;
    private BigDecimal money;
    private ModelError modelError;
}
