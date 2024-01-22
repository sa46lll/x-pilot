package org.xangle.xpilot.core.model.request;

public record SignupRequest(
        String email,
        String name,
        String password
) {

}
