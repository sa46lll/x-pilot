package org.xangle.xpilot.core.model.response;

public record AccessTokenInfo(
        String workerId,
        String email,
        String accessToken
) {

    public static AccessTokenInfo of(String workerId, String email, String accessToken) {
        return new AccessTokenInfo(workerId, email, accessToken);
    }
}
