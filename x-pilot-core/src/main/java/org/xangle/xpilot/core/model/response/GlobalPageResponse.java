package org.xangle.xpilot.core.model.response;

import java.util.List;

public record GlobalPageResponse<T>(
        int page,
        int size,
        int totalPages,
        long totalElements,
        List<T> content
) {
    public static <T> GlobalPageResponse<T> of(int number, int size, int totalPages, long totalElements, List<T> content) {
        return new GlobalPageResponse<>(
                number,
                size,
                totalPages,
                totalElements,
                content
        );
    }
}