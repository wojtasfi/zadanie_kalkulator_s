package com.task.calculator.dto;

import com.task.calculator.constraints.update.CountryCodeUpdateConstraint;
import com.task.calculator.constraints.update.CurrencyCodeUpdateConstraint;
import com.task.calculator.constraints.update.IncomeTaxPercentageUpdateConstraint;

import javax.validation.constraints.Pattern;

public class CountryCostsInformationUpdateDto {

    @CountryCodeUpdateConstraint
    private String countryCode;

    @CurrencyCodeUpdateConstraint
    private String currencyCode;

    @IncomeTaxPercentageUpdateConstraint
    private Double incomeTaxPercentage;

    @Pattern(regexp = "(\\d+)", message = "Incorrect fixed costs format")
    private String fixedCosts;

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

    public String getFixedCosts() {
        return fixedCosts;
    }

    public void setFixedCosts(String fixedCosts) {
        this.fixedCosts = fixedCosts;
    }
}
