package org.xangle.xpilot.batch.converter;

import java.nio.charset.StandardCharsets;

public class ByteConverter {

    private ByteConverter() {
    }

    public static String convertToString(final byte[] bytes) {
        return new String(bytes, StandardCharsets.US_ASCII);
    }
}
