package org.xangle.xpilot.scheduler.service;

import java.nio.charset.StandardCharsets;

public class ByteConverterService {

    private ByteConverterService() {
    }

    public static String convertToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.US_ASCII);
    }
}
