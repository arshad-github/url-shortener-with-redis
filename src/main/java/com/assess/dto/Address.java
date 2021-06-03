package com.assess.dto;

import java.time.LocalDateTime;

public class Address {
    private final String originalUrl;
    private final LocalDateTime createDateTime;

    public Address(String originalUrl, LocalDateTime createDateTime) {
        this.originalUrl = originalUrl;
        this.createDateTime = createDateTime;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public static Address createShort(final String originalUrl) {
        return new Address(originalUrl, LocalDateTime.now());
    }
}
