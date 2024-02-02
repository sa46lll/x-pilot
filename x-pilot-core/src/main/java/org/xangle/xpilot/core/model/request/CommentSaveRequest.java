package org.xangle.xpilot.core.model.request;

public record CommentSaveRequest(
        Long blockNumber,
        String workerId,
        String content
) {

}
