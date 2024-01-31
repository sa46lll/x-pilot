package org.xangle.xpilot.core.model.request;

public record CommentDummyRequest(
        String objectId,
        String rootId,
        String parentId,
        Long depth,
        Long sequence,
        String content,
        String workerId
) {
}
