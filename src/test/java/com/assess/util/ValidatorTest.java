package com.assess.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    private static final String VALID = "https://www.google.com";
    private static final String INVALID = "htt://www.google.com";

    @Test
    public void testValid() {
        assertFalse(Validator.urlInvalid(VALID));
    }

    @Test
    public void testInvalid() {
        assertTrue(Validator.urlInvalid(INVALID));
    }
}