package com.example.paris2024Back.interfaces;

import com.example.paris2024Back.validations.StartDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StartDateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckStartDate {
    String message() default "Start date must be between 26/07/2024 and 11/08/2024";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}