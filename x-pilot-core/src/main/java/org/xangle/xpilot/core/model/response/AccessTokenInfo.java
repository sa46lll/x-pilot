package org.xangle.xpilot.core.model.response;

public record AccessTokenInfo(
        String workerId,
        String email,
        String accessToken
) {

}
