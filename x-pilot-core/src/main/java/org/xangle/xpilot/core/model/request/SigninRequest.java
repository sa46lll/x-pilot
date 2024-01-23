package org.xangle.xpilot.core.model.request;

public record SigninRequest(
        String email,
        String password
) {
}
