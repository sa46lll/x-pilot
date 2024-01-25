package org.xangle.xpilot.core.model;

public record ReplySaveDto(
        Long blockNumber,
        String workerId,
        String parentId,
        String content
) {
    public static ReplySaveDto of(Long blockNumber,
                                  String workerId,
                                  String parentId,
                                  String content) {
        return new ReplySaveDto(blockNumber, workerId, parentId, content);
    }
}
