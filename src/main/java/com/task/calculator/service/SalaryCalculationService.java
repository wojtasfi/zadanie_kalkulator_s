package com.task.calculator.service;

import com.task.calculator.dto.SalaryRequestDto;
import com.task.calculator.entity.CountryCostsInformation;
import com.task.calculator.rest.CalculatorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class SalaryCalculationService {

    private static Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class);

    private static final Integer MONTH_IN_DAYS = 22;

    private static DecimalFormat decimalFormat = new DecimalFormat(".##");


    private CountryCostsInformationService countryCostsInformationService;

    private CurrencyInformationService currencyInformationService;

    @Autowired
    public SalaryCalculationService(CountryCostsInformationService countryCostsInformationService, CurrencyInformationService currencyInformationService) {
        this.countryCostsInformationService = countryCostsInformationService;
        this.currencyInformationService = currencyInformationService;
    }

    public Double calculateSalary(SalaryRequestDto salaryRequestDto) {
        String countryCode = salaryRequestDto.getCountryCode();

        Double monthlyGrossSalary = salaryRequestDto.getDailyGrossSalary() * MONTH_IN_DAYS;

        CountryCostsInformation countryCostsInformation = countryCostsInformationService.findByCountryCode(countryCode);

        String currencyCode = countryCostsInformation.getCurrencyCode();
        Integer fixedCosts = countryCostsInformation.getFixedCosts();
        Double taxPercentage = countryCostsInformation.getIncomeTaxPercentage();

        Double currentCurrRate = 0d;
        try {
            currentCurrRate = currencyInformationService.getCurrentCurrencyRate(currencyCode);
        } catch (Exception e) {
            LOGGER.error("Error occurred when retrieving currency rate for {}", currencyCode, e);
        }
        Double monthlySalaryMinusCosts = monthlyGrossSalary - fixedCosts;

        Double monthlyNetSalary = monthlySalaryMinusCosts - (monthlySalaryMinusCosts * taxPercentage);

        Double salary = Double.valueOf(decimalFormat.format(monthlyNetSalary * currentCurrRate));

        return salary;
    }



}
