package com.task.calculator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryCodeValidator implements
        ConstraintValidator<CountryCodeConstraint, String> {


    @Override
    public void initialize(CountryCodeConstraint countryCodeConstraint) {

    }

    @Override
    public boolean isValid(String countryCode, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }


}
