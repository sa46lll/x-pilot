package org.xangle.xpilot.core.model.response;

public record TokenResponse(
        String email,
        String accessToken
) {

    public static TokenResponse of(String email, String accessToken) {
        return new TokenResponse(email, accessToken);
    }
}
