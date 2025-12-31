package com.github.alxsshv.short_link_creation_service.adapter.in.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DurationStringValidator.class)
public @interface IsValidDurationString {

    String message() default "String must be in duration format ISO 8601";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
