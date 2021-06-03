package com.assess.util;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

public class Hash {
    public static String getHash(String address) {
        return Hashing.crc32c().hashString(address, StandardCharsets.UTF_16).toString();
    }
}
