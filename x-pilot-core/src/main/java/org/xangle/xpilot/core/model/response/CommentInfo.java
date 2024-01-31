package org.xangle.xpilot.core.model.response;

import org.xangle.xpilot.core.entity.CommentEntity;

import java.time.Instant;

public record CommentInfo(
        String id,
        String rootId,
        String parentId,
        Long depth,
        Long sequence,
        String content,
        boolean isDeleted,
        Instant createdTime,
        Instant updatedTime
) {

    public static CommentInfo from(CommentEntity comment) {
        return new CommentInfo(
                comment.getId(),
                comment.getRootId(),
                comment.getParentId(),
                comment.getDepth(),
                comment.getSequence(),
                comment.getContent(),
                comment.isDeleted(),
                comment.getCreatedTime(),
                comment.getUpdatedTime()
        );
    }
}
