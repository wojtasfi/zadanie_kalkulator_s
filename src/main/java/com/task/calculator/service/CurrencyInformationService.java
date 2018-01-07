package com.task.calculator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Iterator;

@Service
public class CurrencyInformationService {
    private static final String RATES_URL = "http://api.nbp.pl/api/exchangerates/rates/A/";
    private static final String LOCAL_CURRENCY = "PLN";


    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private RestTemplate restTemplate;

    public Double getCurrentCurrencyRate(String currencyCode) throws IOException {

        if (currencyCode.equals(LOCAL_CURRENCY)) {
            return 1d;
        }
        String url = RATES_URL + currencyCode;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return retrieveCurrencyRate(responseEntity);
        }
        return 0d;

    }

    private Double retrieveCurrencyRate(ResponseEntity<String> responseEntity) throws IOException {
        String ratesJson = responseEntity.getBody();

        JsonNode rates = objectMapper.readTree(ratesJson).get("rates");

        if (rates.isArray() && rates.size() > 0) {

            Iterator<JsonNode> iterator = rates.iterator();

            if (iterator.hasNext()) {
                JsonNode rate = iterator.next();
                return rate.get("mid").asDouble();
            }

        }
        return 0d;
    }
}
