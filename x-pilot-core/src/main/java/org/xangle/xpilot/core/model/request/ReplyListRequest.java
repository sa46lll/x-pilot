package org.xangle.xpilot.core.model.request;

public record ReplyListRequest(
        Long blockNumber,
        String commentId,
        int page,
        int size
) {
}
