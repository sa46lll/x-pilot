package org.xangle.xpilot.core.model;

public record ReplySaveDto(
        Long blockNumber,
        String workerId,
        String parentId,
        String content
) {
    public static ReplySaveDto of(final Long blockNumber,
                                  final String workerId,
                                  final String parentId,
                                  final String content) {
        return new ReplySaveDto(blockNumber, workerId, parentId, content);
    }
}
