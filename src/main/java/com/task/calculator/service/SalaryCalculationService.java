package com.task.calculator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.calculator.dto.SalaryDto;
import com.task.calculator.dto.SalaryRequestDto;
import com.task.calculator.entity.CountryCostsInformation;
import com.task.calculator.rest.CalculatorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Iterator;

@Service
public class SalaryCalculationService {

    private static Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class);

    private static final Integer MONTH_IN_DAYS = 22;
    private static final String RATES_URL = "http://api.nbp.pl/api/exchangerates/rates/A/";
    private static final String LOCAL_CURRENCY = "PLN";


    @Autowired
    private CountryCostsInformationService countryCostsInformationService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public SalaryDto calculateSalary(SalaryRequestDto salaryRequestDto) {
        String countryCode = salaryRequestDto.getCountryCode();

        Double monthlyGrossSalary = salaryRequestDto.getDailyGrossSalary() * MONTH_IN_DAYS;

        CountryCostsInformation countryCostsInformation = countryCostsInformationService.findByCountryCode(countryCode);

        String currencyCode = countryCostsInformation.getCurrencyCode();
        Integer fixedCosts = countryCostsInformation.getFixedCosts();
        Double taxPercentage = countryCostsInformation.getIncomeTaxPercentage();

        Double currentCurrRate = 0d;
        try {
            currentCurrRate = getCurrentCurrencyRate(currencyCode);
        } catch (Exception e) {
            LOGGER.error("Error occurred when retrieving currency rate for {}", currencyCode, e);
        }
        Double monthlySalaryMinusCosts = monthlyGrossSalary - fixedCosts;

        Double monthlyNetSalary = monthlySalaryMinusCosts - (monthlySalaryMinusCosts * taxPercentage/100);

        Double salary = monthlyNetSalary * currentCurrRate;

        return new SalaryDto(countryCode, salary);
    }


    public Double getCurrentCurrencyRate(String currencyCode) throws IOException {

        if (currencyCode.equals(LOCAL_CURRENCY)) {
            return 1d;
        }
        String url = RATES_URL + currencyCode;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String ratesJson = responseEntity.getBody();

            JsonNode rates = objectMapper.readTree(ratesJson).get("rates");

            if (rates.isArray() && rates.size() > 0) {

                Iterator<JsonNode> iterator = rates.iterator();

                if (iterator.hasNext()) {
                    JsonNode rate = iterator.next();
                    return rate.get("mid").asDouble();
                }

            }
        }
        return 0d;

    }
}
