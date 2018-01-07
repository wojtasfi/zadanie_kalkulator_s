package com.task.calculator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyCodeValidator implements
        ConstraintValidator<CurrencyCodeConstraint, String> {


    @Override
    public void initialize(CurrencyCodeConstraint countryCodeConstraint) {

    }

    @Override
    public boolean isValid(String currencyCode, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }


}
