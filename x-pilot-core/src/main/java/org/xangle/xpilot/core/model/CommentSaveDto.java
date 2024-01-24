package org.xangle.xpilot.core.model;

public record CommentSaveDto(
        Long blockNumber,
        String workerId,
        String content
) {
    public static CommentSaveDto of(Long blockNumber,
                                    String workerId,
                                    String content) {
        return new CommentSaveDto(blockNumber, workerId, content);
    }

}
