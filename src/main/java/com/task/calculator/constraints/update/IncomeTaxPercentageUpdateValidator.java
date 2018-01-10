package com.task.calculator.constraints.update;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IncomeTaxPercentageUpdateValidator implements
        ConstraintValidator<IncomeTaxPercentageUpdateConstraint, Double> {

    private static final String REGEX = "((\\d+)(\\.\\d{1,2}))$";
    private static final Double MIN = 0.01;
    private static final Double MAX = 1d;


    @Override
    public void initialize(IncomeTaxPercentageUpdateConstraint incomeTaxPercentageConstraint) {

    }

    @Override
    public boolean isValid(Double incomeTaxPercentage, ConstraintValidatorContext constraintValidatorContext) {

        if (incomeTaxPercentage == null) {
            return true;
        }
        if (incomeTaxPercentage >= MIN && incomeTaxPercentage <= MAX) {

            String value = incomeTaxPercentage.toString();
            return Pattern.compile(REGEX).matcher(value).matches();
        }
        return false;

    }


}
