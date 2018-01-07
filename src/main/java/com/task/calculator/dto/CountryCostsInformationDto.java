package com.task.calculator.dto;

import com.task.calculator.constraints.CountryCodeConstraint;
import com.task.calculator.constraints.CurrencyCodeConstraint;
import com.task.calculator.constraints.IncomeTaxPercentageConstraint;

import javax.validation.constraints.NotNull;

public class CountryCostsInformationDto {

    @NotNull
    @CountryCodeConstraint
    private String countryCode;

    @NotNull
    @CurrencyCodeConstraint
    private String currencyCode;

    @NotNull
    @IncomeTaxPercentageConstraint
    private Double incomeTaxPercentage;

    @NotNull
    private Integer fixedCosts;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getIncomeTaxPercentage() {
        return incomeTaxPercentage;
    }

    public void setIncomeTaxPercentage(Double incomeTaxPercentage) {
        this.incomeTaxPercentage = incomeTaxPercentage;
    }

    public Integer getFixedCosts() {
        return fixedCosts;
    }

    public void setFixedCosts(Integer fixedCosts) {
        this.fixedCosts = fixedCosts;
    }
}
