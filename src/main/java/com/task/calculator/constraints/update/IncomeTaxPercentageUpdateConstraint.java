package com.task.calculator.constraints.update;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IncomeTaxPercentageUpdateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IncomeTaxPercentageUpdateConstraint {
    String message() default "Invalid percentage format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
