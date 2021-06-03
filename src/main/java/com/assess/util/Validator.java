package com.assess.util;

import org.apache.commons.validator.routines.UrlValidator;

public class Validator {

    public static boolean urlInvalid(String input) {
        String[] schemes = {"http", "https"};
        final UrlValidator urlValidator = new UrlValidator(schemes);

        if (!urlValidator.isValid(input)) {
            return true;
        }
        return false;
    }
}
