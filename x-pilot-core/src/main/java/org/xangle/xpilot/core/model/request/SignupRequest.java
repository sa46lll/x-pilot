package org.xangle.xpilot.core.model.request;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank(message = "Email is mandatory")
        String email,

        @NotBlank(message = "Name is mandatory")
        String name,

        @NotBlank(message = "Password is mandatory")
        String password
) {

}
