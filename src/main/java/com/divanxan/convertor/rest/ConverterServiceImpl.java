package com.divanxan.convertor.rest;

import com.divanxan.convertor.model.ConversionRate;
import com.divanxan.convertor.model.ConverterData;
import com.divanxan.convertor.model.ModelError;
import com.divanxan.convertor.model.ResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
public class ConverterServiceImpl implements ConverterService {

    @Value("${api.base.url}")
    private String API_BASE_URL;

    @Override
    public ConverterData getConverterModel() {
        return new ConverterData();
    }

    @Override
    public ResponseModel getConvertedValue(String source, String target, BigDecimal amount) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setCurrency(source);
        responseModel.setResultCurrency(target);
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(API_BASE_URL);
        String endPoint = urlBuilder.build().encode().toUriString();
        RestTemplate restTemplate = new RestTemplate();
        ConversionRate rates = restTemplate.getForObject(endPoint, ConversionRate.class);

        if (!rates.getRates().isEmpty()) {
            MathContext mc = new MathContext(10, RoundingMode.DOWN);
            BigDecimal a = new BigDecimal(rates.getRates().get(source), mc);
            BigDecimal b = new BigDecimal(rates.getRates().get(target), mc);
            BigDecimal c = b.divide(a, mc);

            responseModel.setMoney(c.multiply(amount));
        } else {
            responseModel.setModelError(new ModelError("Unable to convert conrrency", "400"));
            responseModel.setMoney(new BigDecimal(0));
        }

        return responseModel;

    }
}
