package com.task.calculator.dto;

public class SalaryDto {
    private String countryCode;
    private Double salary;


    public SalaryDto(String countryCode, Double salary) {
        this.countryCode = countryCode;
        this.salary = salary;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Double getSalary() {
        return salary;
    }

}
