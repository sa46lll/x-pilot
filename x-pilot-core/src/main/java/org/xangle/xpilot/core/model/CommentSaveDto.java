package org.xangle.xpilot.core.model;

public record CommentSaveDto(
        Long blockNumber,
        String workerId,
        String rootId,
        String parentId,
        Long depth,
        String content
) {
    public static CommentSaveDto of(Long blockNumber,
                                    String workerId,
                                    String rootId,
                                    String parentId,
                                    Long depth,
                                    String content) {
        return new CommentSaveDto(blockNumber, workerId, rootId, parentId, depth, content);
    }

}
