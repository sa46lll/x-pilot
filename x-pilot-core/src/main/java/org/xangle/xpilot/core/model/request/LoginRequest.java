package org.xangle.xpilot.core.model.request;

public record LoginRequest(
        String email,
        String password
) {
}
