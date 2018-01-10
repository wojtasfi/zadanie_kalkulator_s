package com.task.calculator.constraints.update;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CurrencyCodeUpdateValidator implements
        ConstraintValidator<CurrencyCodeUpdateConstraint, String> {

    private static final String REGEX = "[A-Z]{3}";

    @Override
    public void initialize(CurrencyCodeUpdateConstraint currencyCodeConstraint) {

    }

    @Override
    public boolean isValid(String currencyCode, ConstraintValidatorContext constraintValidatorContext) {

        if (currencyCode == null) {
            return true;
        }
        return Pattern.compile(REGEX).matcher(currencyCode).matches();
    }


}
