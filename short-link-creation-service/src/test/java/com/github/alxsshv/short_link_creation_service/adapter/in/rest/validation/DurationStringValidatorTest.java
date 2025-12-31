package com.github.alxsshv.short_link_creation_service.adapter.in.rest.validation;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DurationStringValidatorTest {


    DurationStringValidator validator = new DurationStringValidator();


    @Test
    void testIsValid_whenStringIsValidTime_thenReturnTrue() {
        String validString = "PT2H30M5S";
        Assertions.assertTrue(validator.isValid(validString, null));
    }

    @Test
    void testIsValid_whenStringIsValidDate_thenReturnTrue() {
        String validString = "P1Y2M4D";
        Assertions.assertTrue(validator.isValid(validString, null));
    }


    @Test
    void testIsValid_whenStringIsNull_thenReturnTrue() {
        String validString = null;
        Assertions.assertTrue(validator.isValid(validString, null));
    }

    @Test
    void testIsValid_whenStringIsEmpty_thenReturnFalse() {
        String validString = "";
        Assertions.assertFalse(validator.isValid(validString, null));
    }

    @Test
    void testIsValid_whenStringNotCorrectDuration_thenReturnFalse() {
        String validString = "Psd21H";
        Assertions.assertFalse(validator.isValid(validString, null));
    }

}
