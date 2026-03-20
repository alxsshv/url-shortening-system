package com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/** Валидатор для проверки корректности указанного времени ttl на соответствие формату ISO-8601 */
public class DurationStringValidator implements ConstraintValidator<IsValidDurationString, String> {

    /** Регулярное выражение для проверки корректности указанного времени ttl на соответствие формату ISO-8601 */
    private static final String ISO_8601_DURATION_REGEX = "^P(?:(\\d+)Y)?(?:(\\d+)M)?(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?$";

    /** Метод проверки корректности указанного времени ttl на соответствие формату ISO-8601 */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        return s.matches(ISO_8601_DURATION_REGEX);
    }
}
