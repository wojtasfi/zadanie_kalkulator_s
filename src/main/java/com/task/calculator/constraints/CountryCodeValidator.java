package com.task.calculator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CountryCodeValidator implements
        ConstraintValidator<CountryCodeConstraint, String> {

    private static final String REGEX = "[A-Z]{2}";

    @Override
    public void initialize(CountryCodeConstraint countryCodeConstraint) {

    }

    @Override
    public boolean isValid(String countryCode, ConstraintValidatorContext constraintValidatorContext) {

        return Pattern.compile(REGEX).matcher(countryCode).matches();
    }


}
