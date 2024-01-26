package org.xangle.xpilot.core.model.response;

import java.util.List;

public record PageableInfo<T>(
        int page,
        int size,
        int totalPages,
        long totalElements,
        List<T> content
) {
    public static <T> PageableInfo<T> of(int number, int size, int totalPages, long totalElements, List<T> content) {
        return new PageableInfo<>(
                number,
                size,
                totalPages,
                totalElements,
                content
        );
    }
}