package com.task.calculator.entity;

import com.task.calculator.constraints.CountryCodeConstraint;
import com.task.calculator.constraints.CurrencyCodeConstraint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class CountryCostsInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @CountryCodeConstraint
    private String countryCode;

    @NotNull
    @CurrencyCodeConstraint
    private String currencyCode;

    @NotNull
    private Integer incomeTaxPercentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getIncomeTaxPercentage() {
        return incomeTaxPercentage;
    }

    public void setIncomeTaxPercentage(Integer incomeTaxPercentage) {
        this.incomeTaxPercentage = incomeTaxPercentage;
    }
}
