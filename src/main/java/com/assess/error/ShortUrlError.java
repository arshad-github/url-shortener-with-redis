package com.assess.error;

public class ShortUrlError {
    private final String errorMessage;

    public ShortUrlError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
