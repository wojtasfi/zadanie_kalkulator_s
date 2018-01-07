package com.task.calculator.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IncomeTaxPercentageValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IncomeTaxPercentageConstraint {
    String message() default "Invalid percentage format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
