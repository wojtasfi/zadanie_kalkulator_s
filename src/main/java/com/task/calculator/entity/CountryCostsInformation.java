package com.task.calculator.entity;

import com.task.calculator.constraints.CountryCodeConstraint;
import com.task.calculator.constraints.CurrencyCodeConstraint;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Max(100)
    @Min(1)
    @NumberFormat(style= NumberFormat.Style.PERCENT)
//    @Pattern(regexp = "((\\d+)(\\.\\d{2}))$")
    private Double incomeTaxPercentage;

    @NotNull
    private Integer fixedCosts;

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
