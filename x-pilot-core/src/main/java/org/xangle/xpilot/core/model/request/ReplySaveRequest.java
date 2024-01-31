package org.xangle.xpilot.core.model.request;

public record ReplySaveRequest(
        Long blockNumber,
        String workerId,
        String parentId,
        String content
) {
    public static ReplySaveRequest of(Long blockNumber,
                                      String workerId,
                                      String parentId,
                                      String content) {
        return new ReplySaveRequest(blockNumber, workerId, parentId, content);
    }
}
