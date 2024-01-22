package org.xangle.xpilot.core.model.response;

public record TokenResponse(
        Long memberId,
        String accessToken
) {
}
