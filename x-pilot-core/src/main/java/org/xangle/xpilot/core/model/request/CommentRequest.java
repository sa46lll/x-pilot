package org.xangle.xpilot.core.model.request;

public record CommentRequest(
        String parentId,
        String content
) {
}
