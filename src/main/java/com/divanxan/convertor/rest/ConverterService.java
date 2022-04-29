package com.divanxan.convertor.rest;

import com.divanxan.convertor.model.ConverterData;
import com.divanxan.convertor.model.ResponseModel;

import java.math.BigDecimal;

public interface ConverterService {

    public ResponseModel getConvertedValue(String source, String target, BigDecimal amount);
    public ConverterData getConverterModel();

}
