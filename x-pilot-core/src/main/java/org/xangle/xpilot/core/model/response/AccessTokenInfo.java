package org.xangle.xpilot.core.model.response;

public record AccessTokenInfo(
        String email,
        String accessToken
) {

    public static AccessTokenInfo of(String email, String accessToken) {
        return new AccessTokenInfo(email, accessToken);
    }
}
