package com.divanxan.convertor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConverterData {
    private String currency;
    private String resultCurrency;
    private BigDecimal money;
    private BigDecimal result;
}
