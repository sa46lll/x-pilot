package org.xangle.xpilot.core.model.request;

public record CommentSaveRequest(
        Long blockNumber,
        String workerId,
        String content
) {
    public static CommentSaveRequest of(Long blockNumber,
                                        String workerId,
                                        String content) {
        return new CommentSaveRequest(blockNumber, workerId, content);
    }

}
