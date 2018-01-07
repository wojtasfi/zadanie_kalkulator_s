package com.task.calculator.dto;

import com.task.calculator.constraints.CountryCodeConstraint;

import javax.validation.constraints.NotNull;


public class SalaryRequestDto {

    @NotNull
    @CountryCodeConstraint
    private String countryCode;

    @NotNull
    private Double dailyGrossSalary;


    public Double getDailyGrossSalary() {
        return dailyGrossSalary;
    }

    public void setDailyGrossSalary(Double dailyGrossSalary) {
        this.dailyGrossSalary = dailyGrossSalary;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
