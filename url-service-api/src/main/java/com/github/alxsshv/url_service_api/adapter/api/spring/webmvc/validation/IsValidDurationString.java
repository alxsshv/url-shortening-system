package com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Аннотация для валидации строки на возможность её парсинга в {@link java.time.Duration}.
 * Проверяет строку на соответствие требованиям к длительности в формате ISO-8601.*/
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DurationStringValidator.class)
public @interface IsValidDurationString {

    String message() default "String must be in duration format ISO 8601";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
