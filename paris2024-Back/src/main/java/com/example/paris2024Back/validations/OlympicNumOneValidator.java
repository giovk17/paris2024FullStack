package com.example.paris2024Back.validations;

import com.example.paris2024Back.interfaces.OlympicNumOneConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class OlympicNumOneValidator implements ConstraintValidator<OlympicNumOneConstraint, Long> {

    @Override
    public void initialize(OlympicNumOneConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long olympicNumOne, ConstraintValidatorContext context) {
        if (olympicNumOne == null) {
            return false;
        }
        String olympicNumOneStr = String.valueOf(olympicNumOne);
        if (olympicNumOneStr.length() != 5) {
            return false;
        }
        if (olympicNumOneStr.startsWith("0")) {
            return false;
        }
        return olympicNumOneStr.charAt(0) != olympicNumOneStr.charAt(olympicNumOneStr.length() - 1);
    }
}