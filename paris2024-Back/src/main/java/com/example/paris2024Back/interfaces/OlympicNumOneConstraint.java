package com.example.paris2024Back.interfaces;

import com.example.paris2024Back.validations.OlympicNumOneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OlympicNumOneValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface OlympicNumOneConstraint {
    String message() default "Olympic number one must consist of 5 digits, not start with 0, and first and last digits must be different";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
