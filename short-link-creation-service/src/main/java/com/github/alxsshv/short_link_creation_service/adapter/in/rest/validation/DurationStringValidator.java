package com.github.alxsshv.short_link_creation_service.adapter.in.rest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DurationStringValidator implements ConstraintValidator<IsValidDurationString, String> {

    private static final String ISO_8601_DURATION_REGEX = "^P(?:(\\d+)Y)?(?:(\\d+)M)?(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        return s.matches(ISO_8601_DURATION_REGEX);
    }
}
