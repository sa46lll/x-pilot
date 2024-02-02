package org.xangle.xpilot.core.model.request;

public record ReplySaveRequest(
        Long blockNumber,
        String workerId,
        String parentId,
        String content
) {

}
