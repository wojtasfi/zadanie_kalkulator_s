package com.task.calculator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CurrencyCodeValidator implements
        ConstraintValidator<CurrencyCodeConstraint, String> {

    private static final String REGEX = "[A-Z]{3}";

    @Override
    public void initialize(CurrencyCodeConstraint currencyCodeConstraint) {

    }

    @Override
    public boolean isValid(String currencyCode, ConstraintValidatorContext constraintValidatorContext) {

        return Pattern.compile(REGEX).matcher(currencyCode).matches();
    }


}
