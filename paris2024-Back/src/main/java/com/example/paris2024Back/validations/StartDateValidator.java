package com.example.paris2024Back.validations;

import com.example.paris2024Back.interfaces.CheckStartDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class StartDateValidator implements ConstraintValidator<CheckStartDate, LocalDate> {
    @Override
    public void initialize(CheckStartDate constraintAnnotation) {
    }


    @Override
    public boolean isValid(LocalDate startDate, ConstraintValidatorContext context) {
        if (startDate == null) {
            return false;
        }
        LocalDate startDateLimit = LocalDate.of(2024, 7, 26);
        LocalDate endDateLimit = LocalDate.of(2024, 8, 11);
        return !startDate.isBefore(startDateLimit) && !startDate.isAfter(endDateLimit);
    }
}