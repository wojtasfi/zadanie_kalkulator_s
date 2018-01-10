package com.task.calculator.constraints.update;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CountryCodeUpdateValidator implements
        ConstraintValidator<CountryCodeUpdateConstraint, String> {

    private static final String REGEX = "[A-Z]{2}";

    @Override
    public void initialize(CountryCodeUpdateConstraint countryCodeConstraint) {

    }

    @Override
    public boolean isValid(String countryCode, ConstraintValidatorContext constraintValidatorContext) {

        if (countryCode == null) {
            return true;
        }
        return Pattern.compile(REGEX).matcher(countryCode).matches();
    }


}
