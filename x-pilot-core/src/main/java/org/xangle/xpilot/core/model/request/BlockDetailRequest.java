package org.xangle.xpilot.core.model.request;

public record BlockDetailRequest(
        Long blockNumber,
        int page,
        int size
) {
}
