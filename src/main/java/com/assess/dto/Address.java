package com.assess.dto;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class Address {
    private final String shortId;
    private final String originalUrl;
    private final LocalDateTime createDateTime;

    public Address(String shortId, String originalUrl, LocalDateTime createDateTime) {
        this.shortId = shortId;
        this.originalUrl = originalUrl;
        this.createDateTime = createDateTime;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public String getShortId() {
        return shortId;
    }

    public static Address createShort(final String originalUrl) {
        final String shortId = Hashing.crc32c().hashString(originalUrl, StandardCharsets.UTF_16).toString();
        return new Address(shortId, originalUrl, LocalDateTime.now());
    }
}
